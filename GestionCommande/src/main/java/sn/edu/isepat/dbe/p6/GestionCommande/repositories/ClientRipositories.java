package sn.edu.isepat.dbe.p6.GestionCommande.repositories;

import java.util.Optional;

import org.springframework.boot.micrometer.metrics.autoconfigure.MetricsProperties.Web.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ClientRipositories extends  JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
    
}
