package sn.edu.isepat.dbe.p6.GestionCommande.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import sn.edu.isepat.dbe.p6.GestionCommande.dto.CommandeDTO;
import sn.edu.isepat.dbe.p6.GestionCommande.entities.Commande;
import sn.edu.isepat.dbe.p6.GestionCommande.services.CommandeService;
import sn.edu.isepat.dbe.p6.GestionCommande.services.LigneCommandeService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/commandes")
@Tag(name = "gestion des commandes", description = "crud de commande")
public class CommandeController {
    private final CommandeService commandeService;
    private final LigneCommandeService ligneCommandeService;

    public CommandeController(CommandeService commandeService, LigneCommandeService ligneCommandeService) {
        this.commandeService = commandeService;
        this.ligneCommandeService = ligneCommandeService;

    }

    @PostMapping
    @Operation(summary = "création", description = "création  de commande")
    public ResponseEntity<CommandeDTO> creerCommande(@RequestBody CommandeDTO dto) {
        CommandeDTO created = commandeService.creerCommande(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    @Operation(summary = "lister", description = "liste  de commande")
    public ResponseEntity<List<CommandeDTO>> getToutesCommandes() {
        return ResponseEntity.ok(commandeService.getToutesCommandes());
    }

    @GetMapping("/{id}")
    @Operation(summary = "liste par id", description = "liste de commande par id")
    public ResponseEntity<CommandeDTO> getCommandeById(@PathVariable Long id) {
        return ResponseEntity.ok(commandeService.getCommandeById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "modification", description = "modification  de commande")
    public ResponseEntity<CommandeDTO> modifierCommande(@PathVariable Long id, @RequestBody CommandeDTO dto) {
        return ResponseEntity.ok(commandeService.modifierCommande(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "suppression", description = "suppression  de commande")
    public ResponseEntity<Void> supprimerCommande(@PathVariable Long id) {
        commandeService.supprimerCommande(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/valider")
    @Operation(summary = "Validation", description = "validation  de commande")
    public ResponseEntity<CommandeDTO> validerCommande(@PathVariable Long id) {
        return ResponseEntity.ok(commandeService.validerCommande(id));
    }

    @PatchMapping("/{id}/annuler")
    @Operation(summary = "annulation", description = "annulation  de commande")
    public ResponseEntity<CommandeDTO> annulerCommande(@PathVariable Long id) {
        return ResponseEntity.ok(commandeService.annulerCommande(id));
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Recherche par client", description = "recherche de commande par le client")
    public ResponseEntity<List<CommandeDTO>> getCommandesParClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(commandeService.getCommandesParClient(clientId));
    }

    @GetMapping("/entre-dates")
    @Operation(summary = "Recherche entre date", description = "recherche de commande ente deux date")
    public ResponseEntity<List<CommandeDTO>> getCommandesEntreDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime debut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(commandeService.getCommandesEntreDates(debut, fin));
    }

    @GetMapping("/statistiques/chiffre-affaires")
    @Operation(summary = "chiffre d'affaires", description = "chiffre d'affaires des commandes")
    public ResponseEntity<Double> getChiffreAffairesGlobal() {
        return ResponseEntity.ok(commandeService.getChiffreAffairesGlobal());
    }

    @GetMapping("/statistiques/par-client")
    @Operation(summary = "Total de commande", description = "total de commande par client")
    public ResponseEntity<List<Commande>> getTotalCommandesParClient() {
        return ResponseEntity.ok(commandeService.getTotalCommandesParClient());
    }
}