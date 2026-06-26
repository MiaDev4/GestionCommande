package sn.edu.isepat.dbe.p6.GestionCommande.controllers;

import sn.edu.isepat.dbe.p6.GestionCommande.entities.Role;
import sn.edu.isepat.dbe.p6.GestionCommande.services.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Tag(name = "Rôles", description = "Gestion des rôles utilisateurs")
public class RoleControllers{
    private final RoleService roleService;

    @PostMapping
    @Operation(summary = "Créer un rôle", description = "Ajoute un nouveau rôle")
    public Role create(@RequestBody Role role) {
        return roleService.create(role);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un rôle", description = "Retourne un rôle par son identifiant")
    public Role getById(@PathVariable Long id) {
        return roleService.getById(id);
    }

    @GetMapping
    @Operation(summary = "Lister les rôles", description = "Retourne la liste de tous les rôles")
    public List<Role> getAll() {
        return roleService.getAll();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un rôle", description = "Supprime un rôle par son identifiant")
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }
}