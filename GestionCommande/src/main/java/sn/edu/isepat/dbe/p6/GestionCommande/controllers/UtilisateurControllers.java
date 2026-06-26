package sn.edu.isepat.dbe.p6.GestionCommande.controllers;
import sn.edu.isepat.dbe.p6.GestionCommande.dto.UtilisateurDto;
import sn.edu.isepat.dbe.p6.GestionCommande.services.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
@RequiredArgsConstructor
@Tag(name = "Utilisateurs", description = "Gestion des utilisateurs")
public class UtilisateurControllers {
    private final UtilisateurService utilisateurService;

    @PostMapping
    @Operation(summary = "Créer un utilisateur", description = "Ajoute un nouvel utilisateur")
    public UtilisateurDto create(@RequestBody UtilisateurDto utilisateur) {
        return utilisateurService.createUtilisateur(utilisateur);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un utilisateur", description = "Retourne un utilisateur par son identifiant")
    public UtilisateurDto getById(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id);
    }

    @GetMapping
    @Operation(summary = "Lister les utilisateurs", description = "Retourne la liste de tous les utilisateurs")
    public List<UtilisateurDto> getAll() {
        return utilisateurService.getAllUtilisateurs();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un utilisateur", description = "Supprime un utilisateur par son identifiant")
    public void delete(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
    }
}