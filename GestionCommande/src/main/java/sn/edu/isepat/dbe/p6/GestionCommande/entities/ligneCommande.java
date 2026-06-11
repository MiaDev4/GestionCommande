package sn.edu.isepat.dbe.p6.GestionCommande.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "ligne_commandes")
@Getter
@Setter
public class LigneCommande {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;


      @ManyToOne
      @JoinColumn(name = "idProduit", nullable = false)
      private Produit produit;

      @Column(nullable = false)
      private Integer quantite;

      @Column(nullable = false, precision = 10, scale = 2)
      private BigDecimal prixUnitaire;

      @ManyToOne
      @JoinColumn(name = "commande_id", nullable = false)
      private Commande commande;
}