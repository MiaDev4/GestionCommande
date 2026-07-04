package sn.edu.isepat.dbe.p6.GestionCommande.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepat.dbe.p6.GestionCommande.dto.ProduitDto;
import sn.edu.isepat.dbe.p6.GestionCommande.services.ProduitService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/produits")
public class ProduitController {
     private final ProduitService produitService;

     @GetMapping
     public List<ProduitDto> getAllProduits() {
          return produitService.getAllProduits();
     }

     @GetMapping("/{id}")
     public ProduitDto getProduit(@PathVariable Long id) {
          return produitService.getProduitById(id);
     }

     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public ProduitDto createProduit(@RequestBody ProduitDto produitDTO) {
          return produitService.createProduit(produitDTO);
     }

     @PutMapping("/{id}")
     public ProduitDto updateProduit(@PathVariable Long id,
                                     @RequestBody ProduitDto produitDTO) {
          return produitService.updateProduit(id, produitDTO);
     }

     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public void deleteProduit(@PathVariable Long id) {
          produitService.deleteProduit(id);
     }
}
