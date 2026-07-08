package sn.edu.isepat.dbe.p6.GestionCommande.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import sn.edu.isepat.dbe.p6.GestionCommande.dto.ProduitDto;
import sn.edu.isepat.dbe.p6.GestionCommande.services.ProduitService;
import java.util.List;

@RestController
@RequestMapping("/api/produits")
@Tag(name = "Gestion produit", description = " crud des produits")
public class ProduitController {
        private final ProduitService produitService;

        // Explicit constructor used instead of Lombok @RequiredArgsConstructor
        public ProduitController(ProduitService produitService) {
                this.produitService = produitService;
        }

        @GetMapping
        @Operation(summary = "Liste", description = "lister tous les produit")
        public List<ProduitDto> getAllProduits() {
                return produitService.getAllProduits();
        }

        @GetMapping("/{id}")
        @Operation(summary = "produit par id", description = "Donne le produit par son id")
        public ProduitDto getProduit(@PathVariable Long id) {
                return produitService.getProduitById(id);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @Operation(summary = "créer produit", description = "création d'un produit")
        public ProduitDto createProduit(@RequestBody ProduitDto produitDTO) {
                return produitService.createProduit(produitDTO);
        }

        @PutMapping("/{id}")
        @Operation(summary = "modifier", description = "modification d'un produit")
        public ProduitDto updateProduit(@PathVariable Long id,
                        @RequestBody ProduitDto produitDTO) {
                return produitService.updateProduit(id, produitDTO);
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        @Operation(summary = "suppression", description = "suppression d'un produit")
        public void deleteProduit(@PathVariable Long id) {
                produitService.deleteProduit(id);
        }
}
