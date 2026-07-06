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
   public ClientEntity updateClient(Long id, ClientEntity client) {

        ClientEntity clientExistant = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        clientExistant.setNom(client.getNom());
        clientExistant.setEmail(client.getEmail());
        clientExistant.setEmail(client.getEmail());

        return clientRepository.save(clientExistant);
    }
        

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
