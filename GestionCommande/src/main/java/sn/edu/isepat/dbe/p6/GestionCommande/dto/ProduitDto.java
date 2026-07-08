package sn.edu.isepat.dbe.p6.GestionCommande.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProduitDto {
     private Long id;

     private String nom;

     private BigDecimal prix;

     private Integer stock;

     private Long createurId;
}
