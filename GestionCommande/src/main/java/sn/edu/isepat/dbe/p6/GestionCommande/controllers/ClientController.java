package sn.edu.isepat.dbe.p6.GestionCommande.controllers;

import sn.edu.isepat.dbe.p6.GestionCommande.entities.ClientEntity;
import sn.edu.isepat.dbe.p6.GestionCommande.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Tag(name = "Clients", description = "Gestion des clients")
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    @Operation(summary = "Créer un client", description = "Ajoute un nouveau client à la base")
    public ClientEntity create(@RequestBody ClientEntity client) {
        return clientService.create(client);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un client", description = "Retourne un client par son identifiant")
    public ClientEntity getById(@PathVariable Long id) {
        return clientService.getById(id);
    }

    @GetMapping
    @Operation(summary = "Lister les clients", description = "Retourne la liste de tous les clients")
    public List<ClientEntity> getAll() {
        return clientService.getAll();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un client", description = "Supprime un client par son identifiant")
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }
}