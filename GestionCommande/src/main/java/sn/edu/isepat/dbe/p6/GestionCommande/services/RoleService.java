package sn.edu.isepat.dbe.p6.GestionCommande.services;

import java.util.List;

import org.springframework.stereotype.Service;

import sn.edu.isepat.dbe.p6.GestionCommande.entities.ClientEntity;
import sn.edu.isepat.dbe.p6.GestionCommande.entities.Role;
import sn.edu.isepat.dbe.p6.GestionCommande.repositories.RoleRepositories;
@Service
public class RoleService {

    public RoleService(sn.edu.isepat.dbe.p6.GestionCommande.repositories.RoleRepositories roleRepository) {
        this.roleRepository = roleRepository;
    }
    private final RoleRepositories roleRepository;

    public Role create(Role role) {
        return roleRepository.save(role);
    }

    public Role getById(Long id) {
        return roleRepository.findById(id).orElseThrow();
    }

    public List<Role> getAll() {
        return roleRepository.findAll();
    }
    public Role updateClient(Long id, Role role) {
        Role roletExistant = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));
        roletExistant.setNom(role.getNom());
        return roleRepository.save(roletExistant);
    }
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
    
}
