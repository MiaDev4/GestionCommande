package sn.edu.isepat.dbe.p6.GestionCommande.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sn.edu.isepat.dbe.p6.GestionCommande.dto.ProduitDto;
import sn.edu.isepat.dbe.p6.GestionCommande.entities.Produit;
import sn.edu.isepat.dbe.p6.GestionCommande.exceptions.ApiException;
import sn.edu.isepat.dbe.p6.GestionCommande.repositories.ProduitRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProduitServiceImpl implements ProduitService{
     private final ProduitRepository produitRepository;

     @Override
     public ProduitDto createProduit(ProduitDto dto) {
          log.info("Création d'un produit");

          if (produitRepository.existsByNom(dto.getNom())) {
               throw new ApiException(409, "Le produit existe déjà.");
          }

          Produit produit = new Produit();
          produit.setNom(dto.getNom());
          produit.setPrix(dto.getPrix());
          produit.setStock(dto.getStock());

          Produit saved = produitRepository.save(produit);

          return convertToDTO(saved);
     }

     @Override
     public ProduitDto updateProduit(Long id, ProduitDto dto) {
          Produit produit = produitRepository.findById(id)
                  .orElseThrow(() ->
                          new ApiException(404, "Produit introuvable."));

          produit.setNom(dto.getNom());
          produit.setPrix(dto.getPrix());
          produit.setStock(dto.getStock());

          Produit updated = produitRepository.save(produit);

          return convertToDTO(updated);
     }

     @Override
     public ProduitDto getProduitById(Long id) {
          Produit produit = produitRepository.findById(id)
                  .orElseThrow(() ->
                          new ApiException(404, "Produit introuvable."));

          return convertToDTO(produit);
     }

     @Override
     public List<ProduitDto> getAllProduits() {
          log.info("Récupération de tous les produits");

          return produitRepository.findAll()
                  .stream()
                  .map(this::convertToDTO)
                  .toList();
     }

     @Override
     public void deleteProduit(Long id) {

     }
     /**
      * Conversion Entity -> DTO
      */
     private ProduitDto convertToDTO(Produit produit) {

          ProduitDto dto = new ProduitDto();

          dto.setId(produit.getId());
          dto.setNom(produit.getNom());
          dto.setPrix(produit.getPrix());
          dto.setStock(produit.getStock());

          if (produit.getCreateur() != null) {
               dto.setCreateurId(produit.getCreateur().getId());
          }

          return dto;
     }
}
