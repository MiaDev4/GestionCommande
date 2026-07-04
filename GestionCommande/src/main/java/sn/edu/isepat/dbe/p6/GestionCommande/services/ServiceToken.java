package sn.edu.isepat.dbe.p6.GestionCommande.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sn.edu.isepat.dbe.p6.GestionCommande.dto.TokenDTO;
import sn.edu.isepat.dbe.p6.GestionCommande.dto.TokenRequest;
import sn.edu.isepat.dbe.p6.GestionCommande.entities.Token;
import sn.edu.isepat.dbe.p6.GestionCommande.entities.Utilisateur;
import sn.edu.isepat.dbe.p6.GestionCommande.repositories.TokenRepository;
import sn.edu.isepat.dbe.p6.GestionCommande.repositories.Utilisateurripositories;

@Service
@Transactional
public class ServiceToken {

    @Autowired private TokenRepository tokenRepository;
    @Autowired private Utilisateurripositories utilisateurRepository;

    public TokenDTO creer(TokenRequest request) {
        Utilisateur user = utilisateurRepository.findById(request.utilisateurId())
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Token token = new Token();
        token.setValeur(request.valeur());
        token.setDateExpiration(request.dateExpiration());
        token.setUtilisateur(user);
        return toDTO(tokenRepository.save(token));
    }

    public List<TokenDTO> getTokensUtilisateur(Long userId) {
        return tokenRepository.findByUtilisateurId(userId).stream()
            .map(this::toDTO).toList();
    }

    public void revoquer(Long id) {
        Token token = tokenRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Token non trouvé"));
        token.setRevoque(true);
        tokenRepository.save(token);
    }

    private TokenDTO toDTO(Token token) {
        return new TokenDTO(token.getId(), token.getValeur(), 
            token.getDateExpiration(), token.isRevoque());
    }
    
}
