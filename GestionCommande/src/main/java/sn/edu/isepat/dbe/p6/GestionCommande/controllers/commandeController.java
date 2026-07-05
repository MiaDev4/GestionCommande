package sn.edu.isepat.dbe.p6.GestionCommande.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepat.dbe.p6.GestionCommande.dto.CommandeDTO;
import sn.edu.isepat.dbe.p6.GestionCommande.dto.LigneCommandeDTO;
import sn.edu.isepat.dbe.p6.GestionCommande.entities.Commande;
import sn.edu.isepat.dbe.p6.GestionCommande.services.CommandeService;
import sn.edu.isepat.dbe.p6.GestionCommande.services.LigneCommandeService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {
    private final CommandeService commandeService;
    private final LigneCommandeService ligneCommandeService;

    public CommandeController(CommandeService commandeService, LigneCommandeService ligneCommandeService) {
        this.commandeService = commandeService;
        this.ligneCommandeService = ligneCommandeService;

    }

    @PostMapping
    public ResponseEntity<CommandeDTO> creerCommande(@RequestBody CommandeDTO dto) {
        CommandeDTO created = commandeService.creerCommande(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<CommandeDTO>> getToutesCommandes() {
        return ResponseEntity.ok(commandeService.getToutesCommandes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeDTO> getCommandeById(@PathVariable Long id) {
        return ResponseEntity.ok(commandeService.getCommandeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommandeDTO> modifierCommande(@PathVariable Long id, @RequestBody CommandeDTO dto) {
        return ResponseEntity.ok(commandeService.modifierCommande(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerCommande(@PathVariable Long id) {
        commandeService.supprimerCommande(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/valider")
    public ResponseEntity<CommandeDTO> validerCommande(@PathVariable Long id) {
        return ResponseEntity.ok(commandeService.validerCommande(id));
    }

    @PatchMapping("/{id}/annuler")
    public ResponseEntity<CommandeDTO> annulerCommande(@PathVariable Long id) {
        return ResponseEntity.ok(commandeService.annulerCommande(id));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CommandeDTO>> getCommandesParClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(commandeService.getCommandesParClient(clientId));
    }

    @GetMapping("/entre-dates")
    public ResponseEntity<List<CommandeDTO>> getCommandesEntreDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime debut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(commandeService.getCommandesEntreDates(debut, fin));
    }

    @GetMapping("/statistiques/chiffre-affaires")
    public ResponseEntity<Double> getChiffreAffairesGlobal() {
        return ResponseEntity.ok(commandeService.getChiffreAffairesGlobal());
    }

    @GetMapping("/statistiques/par-client")
    public ResponseEntity<List<Commande>> getTotalCommandesParClient() {
        return ResponseEntity.ok(commandeService.getTotalCommandesParClient());
    }

    @PostMapping("/{commandeId}/lignes")
    public ResponseEntity<LigneCommandeDTO> ajouterLigne(
            @PathVariable Long commandeId,
            @RequestBody LigneCommandeDTO dto) {

        LigneCommandeDTO ligne = ligneCommandeService.ajouterLigne(commandeId, dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(ligne);
    }
}