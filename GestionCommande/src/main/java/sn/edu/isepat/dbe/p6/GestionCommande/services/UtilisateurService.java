package sn.edu.isepat.dbe.p6.GestionCommande.services;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import sn.edu.isepat.dbe.p6.GestionCommande.dto.UtilisateurDto;
import sn.edu.isepat.dbe.p6.GestionCommande.entities.Utilisateur;
import sn.edu.isepat.dbe.p6.GestionCommande.repositories.RoleRepositories;
import sn.edu.isepat.dbe.p6.GestionCommande.repositories.Utilisateurripositories;




@Service
@RequiredArgsConstructor
public class UtilisateurService {
    private final Utilisateurripositories utilisateurRipositories;
    private final RoleRepositories roleRepositories;

    private UtilisateurDto toDTO(Utilisateur utilisateur) {
        return new UtilisateurDto();
    }

    @Transactional(readOnly = true)
    public List<UtilisateurDto> getAllUtilisateurs() {
        return utilisateurRipositories.findAll()
                .stream().map(this::toDTO)
                .collect(Collectors.toList());
    }
    @Transactional
    public UtilisateurDto createUtilisateur(UtilisateurDto utilisateurDto) {
        // Vérifier si le nom d'utilisateur ou l'email existe déjà
        if (utilisateurRipositories.findByEmail(utilisateurDto.getEmail()).isPresent() ||
            utilisateurRipositories.findByNomUtilisateur(utilisateurDto.getNomUtilisateur()).isPresent()) {
            throw new IllegalArgumentException("Le nom d'utilisateur ou l'email existe déjà");
        }

        // Créer un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNomUtilisateur(utilisateurDto.getNomUtilisateur());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setMotPasse(utilisateurDto.getMotPasse());
        utilisateur.setEtatCompte(true); 

        // Assigner les rôles à l'utilisateur
        if (utilisateurDto.getRoles() != null) {
            var roles = utilisateurDto.getRoles().stream()
                    .map(role -> roleRepositories.findByNom(role.getNom())
                            .orElseThrow(() -> new IllegalArgumentException("Rôle non trouvé: " + role.getNom())))
                    .collect(Collectors.toSet());
            utilisateur.setRoles(roles);
        }

        // Enregistrer l'utilisateur dans la base de données
        Utilisateur savedUtilisateur = utilisateurRipositories.save(utilisateur);
        return toDTO(savedUtilisateur);
        
    }
    @Transactional
    public void deleteUtilisateur(long id) {
        if (!utilisateurRipositories.existsById(id)) {
            throw new IllegalArgumentException("Utilisateur non trouvé avec l'id: " + id);
        }
        utilisateurRipositories.deleteById(id);
    }

    
}

