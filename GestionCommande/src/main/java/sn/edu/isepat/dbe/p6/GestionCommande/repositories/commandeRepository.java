package sn.edu.isepat.dbe.p6.GestionCommande.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sn.edu.isepat.dbe.p6.GestionCommande.entities.Commande;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
      List<Commande> findByClientId(Long clientId);

      List<Commande> findByDateCommandeBetween(LocalDateTime debut, LocalDateTime fin);

      List<Commande> findByStatus(Commande.StatutCommande status);

      @Query("SELECT c FROM Commande c")
      List<Commande> totalCommandesParClient();
      
      @Query("SELECT COALESCE(SUM(l.prixUnitaire * l.quantite), 0) FROM LigneCommande l")
      Double calculerChiffreAffairesGlobal();
}