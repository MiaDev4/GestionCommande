package sn.edu.isepat.dbe.p6.GestionCommande.services;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import sn.edu.isepat.dbe.p6.GestionCommande.entities.ClientEntity;
import sn.edu.isepat.dbe.p6.GestionCommande.repositories.ClientRipositories;
@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRipositories clientRepository;
    
    public ClientEntity create(ClientEntity client) {
        return clientRepository.save(client);
    }

    public ClientEntity getById(Long id) {
        return clientRepository.findById(id).orElseThrow();
    }
    public List<ClientEntity> getAll() {
        return clientRepository.findAll();
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
