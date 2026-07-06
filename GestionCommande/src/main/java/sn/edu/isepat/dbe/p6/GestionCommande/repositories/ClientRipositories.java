package sn.edu.isepat.dbe.p6.GestionCommande.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.edu.isepat.dbe.p6.GestionCommande.entities.ClientEntity;

public interface  ClientRipositories extends  JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findByEmail(String email);
    
}
