package sn.edu.isepat.dbe.p6.GestionCommande.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.edu.isepat.dbe.p6.GestionCommande.entities.LigneCommande;

import java.util.List;

@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {
      List<LigneCommande> findByCommandeId(Long commandeId);

      List<LigneCommande> findByIdProduit(Long idProduit);
}