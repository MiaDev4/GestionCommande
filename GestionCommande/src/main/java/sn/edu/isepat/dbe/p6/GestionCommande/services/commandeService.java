package sn.edu.isepat.dbe.p6.GestionCommande.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.edu.isepat.dbe.p6.GestionCommande.dto.CommandeDTO;
import sn.edu.isepat.dbe.p6.GestionCommande.dto.LigneCommandeDTO;
import sn.edu.isepat.dbe.p6.GestionCommande.entities.Commande;
import sn.edu.isepat.dbe.p6.GestionCommande.entities.LigneCommande;
import sn.edu.isepat.dbe.p6.GestionCommande.exceptions.CommandeException;
import sn.edu.isepat.dbe.p6.GestionCommande.repositories.CommandeRepository;
import sn.edu.isepat.dbe.p6.GestionCommande.repositories.LigneCommandeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandeService {
    private final CommandeRepository commandeRepository;
    private final LigneCommandeRepository ligneCommandeRepository;

    public CommandeService(CommandeRepository commandeRepository, LigneCommandeRepository ligneCommandeRepository) {
        this.commandeRepository = commandeRepository;
        this.ligneCommandeRepository = ligneCommandeRepository;
    }

    @Transactional
    public CommandeDTO creerCommande(CommandeDTO dto) {
        Commande commande = new Commande();
        commande.setDateCommande(LocalDateTime.now());
        commande.setStatus(Commande.StatutCommande.CREATED);

        // ⚠️ à adapter quand ton coéquipier crée Client
        // Client client = clientRepository.findById(dto.getClientId())
        // .orElseThrow(() -> new ResourceNotFoundException("Client introuvable"));
        // commande.setClient(client);

        Commande saved = commandeRepository.save(commande);
        return toDTO(saved);
    }

    // Récupérer toutes les commandes
    public List<CommandeDTO> getToutesCommandes() {
        return commandeRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Récupérer une commande par id
    public CommandeDTO getCommandeById(Long id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> CommandeException.notFound(id));
        return toDTO(commande);
    }

    // Modifier une commande
    @Transactional
    public CommandeDTO modifierCommande(Long id, CommandeDTO dto) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> CommandeException.notFound(id));

        if (commande.getStatus() == Commande.StatutCommande.VALIDATED ||
                commande.getStatus() == Commande.StatutCommande.CANCELLED) {
            throw CommandeException.badRequest("message");
        }

        commande.setStatus(dto.getStatus());
        Commande updated = commandeRepository.save(commande);
        return toDTO(updated);
    }

    // Supprimer une commande
    @Transactional
    public void supprimerCommande(Long id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> CommandeException.notFound(id));

        if (commande.getStatus() == Commande.StatutCommande.VALIDATED) {
            throw CommandeException.badRequest("message");
        }

        commandeRepository.deleteById(id);
    }

    // -------------------------
    // Règles métier avancées
    // -------------------------

    // Valider une commande
    @Transactional
    public CommandeDTO validerCommande(Long id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> CommandeException.notFound(id));

        if (commande.getStatus() != Commande.StatutCommande.CREATED) {
            throw CommandeException.badRequest("message");
        }

        if (commande.getLignes().isEmpty()) {
            throw CommandeException.badRequest("message");
        }

        commande.setStatus(Commande.StatutCommande.VALIDATED);
        Commande updated = commandeRepository.save(commande);
        return toDTO(updated);
    }

    // Annuler une commande
    @Transactional
    public CommandeDTO annulerCommande(Long id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> CommandeException.notFound(id));

        if (commande.getStatus() == Commande.StatutCommande.CANCELLED) {
            throw CommandeException.badRequest("message");
        }

        if (commande.getStatus() == Commande.StatutCommande.VALIDATED) {
            throw CommandeException.badRequest("message");
        }

        commande.setStatus(Commande.StatutCommande.CANCELLED);
        Commande updated = commandeRepository.save(commande);
        return toDTO(updated);
    }

    // -------------------------
    // Requêtes avancées
    // -------------------------

    // Commandes d'un client
    public List<CommandeDTO> getCommandesParClient(Long clientId) {
        return commandeRepository.findByClientId(clientId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Commandes entre deux dates
    public List<CommandeDTO> getCommandesEntreDates(LocalDateTime debut, LocalDateTime fin) {
        return commandeRepository.findByDateCommandeBetween(debut, fin)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Chiffre d'affaires global
    public Double getChiffreAffairesGlobal() {
        Double ca = commandeRepository.calculerChiffreAffairesGlobal();
        return ca != null ? ca : 0.0;
    }

    // Total des commandes par client
    public List<Commande> getTotalCommandesParClient() {
        return commandeRepository.totalCommandesParClient();
    }

    // -------------------------
    // Mapper entité -> DTO
    // -------------------------

    private CommandeDTO toDTO(Commande commande) {
        CommandeDTO dto = new CommandeDTO();
        dto.setId(commande.getId());
        dto.setDateCommande(commande.getDateCommande());
        dto.setStatus(commande.getStatus());

        if (commande.getClientId() != null) {
            dto.setIdClient(commande.getClientId());
        }

        List<LigneCommandeDTO> lignesDTO = commande.getLignes()
                .stream()
                .map(this::toLigneDTO)
                .collect(Collectors.toList());
        dto.setLignes(lignesDTO);

        double total = commande.getLignes().stream()
                .mapToDouble(
                        l -> l.getPrixUnitaire() != null ? l.getPrixUnitaire().doubleValue() * l.getQuantite() : 0.0)
                .sum();
        dto.setTotal(total);

        return dto;
    }

    private LigneCommandeDTO toLigneDTO(LigneCommande ligne) {
        LigneCommandeDTO dto = new LigneCommandeDTO();
        dto.setId(ligne.getId());
        dto.setQuantite(ligne.getQuantite());
        dto.setPrixUnitaire(ligne.getPrixUnitaire());

        if (ligne.getProduitId() != null) {
            dto.setIdProduit(ligne.getProduitId());
        }

        if (ligne.getPrixUnitaire() != null && ligne.getQuantite() != null) {
            dto.setSousTotal(ligne.getPrixUnitaire()
                    .multiply(java.math.BigDecimal.valueOf(ligne.getQuantite())));
        }

        return dto;
    }
}