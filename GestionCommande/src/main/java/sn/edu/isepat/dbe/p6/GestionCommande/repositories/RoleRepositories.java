package sn.edu.isepat.dbe.p6.GestionCommande.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.edu.isepat.dbe.p6.GestionCommande.entities.Role;

public interface  RoleRepositories extends JpaRepository<Role, Long> {
    Optional<Role> findByNom(String nom);
    
}
