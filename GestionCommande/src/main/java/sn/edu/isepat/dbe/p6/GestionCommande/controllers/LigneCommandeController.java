package sn.edu.isepat.dbe.p6.GestionCommande.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestBody;
import sn.edu.isepat.dbe.p6.GestionCommande.dto.LigneCommandeDTO;
import sn.edu.isepat.dbe.p6.GestionCommande.services.LigneCommandeService;

@RestController
@RequestMapping("/api/ligneCommande")
@Tag(name = "créer ligne de commande", description = "gestion ligne Commande")
public class LigneCommandeController {

    // injection du service
    private final LigneCommandeService ligneCommandeService;

    public LigneCommandeController(LigneCommandeService ligneCommandeService) {
        this.ligneCommandeService = ligneCommandeService;
    }

    @PostMapping("/{commandeId}")
    @Operation(summary = "ajout", description = "Ajouter une ligne de commande")
    public ResponseEntity<LigneCommandeDTO> ajouterLigne(
            @PathVariable Long commandeId,
            @RequestBody LigneCommandeDTO dto) {

        LigneCommandeDTO ligne = ligneCommandeService.ajouterLigne(commandeId, dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(ligne);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modification", description = "Modifierù une ligne de commande")
    public ResponseEntity<LigneCommandeDTO> modifierLigne(
            @PathVariable Long id,
            @RequestBody LigneCommandeDTO dto) {

        LigneCommandeDTO ligne = ligneCommandeService.modifierLigne(id, dto);

        return ResponseEntity.ok(ligne);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer", description = "supprimer une ligne de commande")
    public ResponseEntity<Void> supprimerLigne(@PathVariable Long id) {

        ligneCommandeService.supprimerLigne(id);

        return ResponseEntity.noContent().build();
    }

}
