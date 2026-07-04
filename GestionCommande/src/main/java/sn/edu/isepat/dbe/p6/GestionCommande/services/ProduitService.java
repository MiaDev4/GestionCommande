package sn.edu.isepat.dbe.p6.GestionCommande.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sn.edu.isepat.dbe.p6.GestionCommande.dto.ProduitDto;
import sn.edu.isepat.dbe.p6.GestionCommande.exceptions.ApiException;
import sn.edu.isepat.dbe.p6.GestionCommande.entities.Produit;
import sn.edu.isepat.dbe.p6.GestionCommande.repositories.ProduitRepository;

import java.util.List;
import java.util.Optional;

public interface ProduitService {

     ProduitDto createProduit(ProduitDto produitDTO);

     ProduitDto updateProduit(Long id, ProduitDto produitDTO);

     ProduitDto getProduitById(Long id);

     List<ProduitDto> getAllProduits();

     void deleteProduit(Long id);
}
