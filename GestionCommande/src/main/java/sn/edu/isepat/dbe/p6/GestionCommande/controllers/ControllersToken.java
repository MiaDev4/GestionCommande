package sn.edu.isepat.dbe.p6.GestionCommande.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import sn.edu.isepat.dbe.p6.GestionCommande.dto.TokenDTO;
import sn.edu.isepat.dbe.p6.GestionCommande.dto.TokenRequest;
import sn.edu.isepat.dbe.p6.GestionCommande.services.ServiceToken;

@RestController
@RequestMapping("/api/tokens")
public class ControllersToken {

    @Autowired private ServiceToken tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> creer(@RequestBody @Valid TokenRequest request) {
        return ResponseEntity.ok(tokenService.creer(request));
    }

    @GetMapping("/utilisateur/{userId}")
    public ResponseEntity<List<TokenDTO>> getTokens(@PathVariable Long userId) {
        return ResponseEntity.ok(tokenService.getTokensUtilisateur(userId));
    }

    @PatchMapping("/{id}/revoquer")
    public ResponseEntity<Void> revoquer(@PathVariable Long id) {
        tokenService.revoquer(id);
        return ResponseEntity.noContent().build();
    }
    
}
