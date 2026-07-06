package sn.edu.isepat.dbe.p6.GestionCommande.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.edu.isepat.dbe.p6.GestionCommande.entities.Utilisateur;

public interface Utilisateurripositories extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmail(String email);
    Optional<Utilisateur> findByNomUtilisateur(String nomUtilisateur);
}
