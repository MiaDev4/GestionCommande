package sn.edu.isepat.dbe.p6.GestionCommande.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sn.edu.isepat.dbe.p6.GestionCommande.entity.Commande;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
      List<Commande> findByClientId(Long clientId);

      List<Commande> findByDateCommandeBetween(LocalDateTime debut, LocalDateTime fin);

      List<Commande> findByStatus(Commande.StatutCommande status);

      List<Object[]> totalCommandesParClient();
}