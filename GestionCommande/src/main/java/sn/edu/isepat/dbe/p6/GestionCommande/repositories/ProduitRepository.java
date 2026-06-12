package sn.edu.isepat.dbe.p6.GestionCommande.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.edu.isepat.dbe.p6.GestionCommande.entities.Produit;

import java.util.Optional;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
     Optional<Produit> findByNom(String email);
}
