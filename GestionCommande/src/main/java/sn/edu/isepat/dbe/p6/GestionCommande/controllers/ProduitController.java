package sn.edu.isepat.dbe.p6.GestionCommande.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
     @Operation(
             summary = "Lister tous les produits",
             description = "Retourne la liste de tous les produits enregistrés.",
             responses = {
                     @ApiResponse(
                             responseCode = "200",
                             description = "Liste des produits récupérée avec succès",
                             content = @Content(
                                     mediaType = "application/json",
                                     schema = @Schema(implementation = ProduitDto.class),
                                     examples = @ExampleObject(
                                             name = "Exemple de réponse",
                                             value = """
                                                [
                                                  {
                                                    "id": 1,
                                                    "nom": "Ordinateur Portable",
                                                    "prix": 450000,
                                                    "stock": 15,
                                                    "createurId": 1
                                                  },
                                                  {
                                                    "id": 2,
                                                    "nom": "Clavier",
                                                    "prix": 15000,
                                                    "stock": 30,
                                                    "createurId": 1
                                                  }
                                                ]
                                                """
                                     )
                             )
                     )
             }
     )
     @GetMapping
     public List<ProduitDto> getAllProduits() {
          return produitService.getAllProduits();
     }

     @Operation(
             summary = "Rechercher un produit",
             description = "Retourne les informations d'un produit à partir de son identifiant.",
             responses = {
                     @ApiResponse(
                             responseCode = "200",
                             description = "Produit trouvé",
                             content = @Content(
                                     schema = @Schema(implementation = ProduitDto.class)
                             )
                     ),
                     @ApiResponse(
                             responseCode = "404",
                             description = "Produit introuvable"
                     )
             }
     )
     @GetMapping("/{id}")
     public ProduitDto getProduit(@PathVariable Long id) {
          return produitService.getProduitById(id);
     }

     @Operation(
             summary = "Créer un produit",
             description = "Permet d'ajouter un nouveau produit.",
             responses = {
                     @ApiResponse(
                             responseCode = "201",
                             description = "Produit créé avec succès",
                             content = @Content(
                                     schema = @Schema(implementation = ProduitDto.class)
                             )
                     ),
                     @ApiResponse(
                             responseCode = "400",
                             description = "Données invalides"
                     )
             }
     )
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
