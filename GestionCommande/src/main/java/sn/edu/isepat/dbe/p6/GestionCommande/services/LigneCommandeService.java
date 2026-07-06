package sn.edu.isepat.dbe.p6.GestionCommande.services;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sn.edu.isepat.dbe.p6.GestionCommande.dto.LigneCommandeDTO;
import sn.edu.isepat.dbe.p6.GestionCommande.entities.Commande;
import sn.edu.isepat.dbe.p6.GestionCommande.entities.LigneCommande;
import sn.edu.isepat.dbe.p6.GestionCommande.exceptions.CommandeException;
import sn.edu.isepat.dbe.p6.GestionCommande.repositories.CommandeRepository;
import sn.edu.isepat.dbe.p6.GestionCommande.repositories.LigneCommandeRepository;

@Service
@Transactional
public class LigneCommandeService {

    private final LigneCommandeRepository ligneCommandeRepository;
    private final CommandeRepository commandeRepository;

    public LigneCommandeService(LigneCommandeRepository ligneCommandeRepository,
                                CommandeRepository commandeRepository) {
        this.ligneCommandeRepository = ligneCommandeRepository;
        this.commandeRepository = commandeRepository;
    }

    public LigneCommandeDTO ajouterLigne(Long commandeId, LigneCommandeDTO dto) {

    Commande commande = commandeRepository.findById(commandeId)
            .orElseThrow(() ->
                    CommandeException.badRequest("Commande introuvable"));

    LigneCommande ligne = new LigneCommande();

    ligne.setCommande(commande);
    ligne.setIdProduit(dto.getIdProduit());
    ligne.setQuantite(dto.getQuantite());
    ligne.setPrixUnitaire(dto.getPrixUnitaire());

    LigneCommande saved = ligneCommandeRepository.save(ligne);

    commande.getLignes().add(saved);

    return toDTO(saved);
}

private LigneCommandeDTO toDTO(LigneCommande ligne) {

    LigneCommandeDTO dto = new LigneCommandeDTO();

    dto.setId(ligne.getId());
    dto.setIdProduit(ligne.getIdProduit());
    dto.setQuantite(ligne.getQuantite());
    dto.setPrixUnitaire(ligne.getPrixUnitaire());

    if (ligne.getPrixUnitaire() != null && ligne.getQuantite() != null) {
        dto.setSousTotal(
                ligne.getPrixUnitaire()
                        .multiply(java.math.BigDecimal.valueOf(ligne.getQuantite()))
        );
    }

    return dto;
}

public List<LigneCommandeDTO> getLignesParCommande(Long commandeId) {

    return ligneCommandeRepository.findByCommandeId(commandeId)
            .stream()
            .map(this::toDTO)
            .toList();
}

public LigneCommandeDTO modifierLigne(Long id, LigneCommandeDTO dto) {

    LigneCommande ligne = ligneCommandeRepository.findById(id)
            .orElseThrow(() ->
                    CommandeException.badRequest("Ligne de commande introuvable"));

    ligne.setQuantite(dto.getQuantite());
    ligne.setPrixUnitaire(dto.getPrixUnitaire());

    LigneCommande updated = ligneCommandeRepository.save(ligne);

    return toDTO(updated);
}

public void supprimerLigne(Long id) {

    LigneCommande ligneCommande = ligneCommandeRepository.findById(id)
            .orElseThrow(() ->
                    CommandeException.badRequest("Ligne de commande n'existe pas"));
    ligneCommandeRepository.delete
    (ligneCommande);

}

}
