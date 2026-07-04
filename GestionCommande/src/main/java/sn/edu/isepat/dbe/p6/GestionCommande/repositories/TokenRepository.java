package sn.edu.isepat.dbe.p6.GestionCommande.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.edu.isepat.dbe.p6.GestionCommande.entities.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findByUtilisateurId(Long utilisateurId);
}
