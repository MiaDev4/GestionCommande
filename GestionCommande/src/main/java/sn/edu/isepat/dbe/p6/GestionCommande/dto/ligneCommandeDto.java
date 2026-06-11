package sn.edu.isepat.dbe.p6.GestionCommande.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LigneCommandeDTO {

      private Long id;
      private Long idProduit;
      private Integer quantite;
      private BigDecimal prixUnitaire;
      private BigDecimal sousTotal;
}