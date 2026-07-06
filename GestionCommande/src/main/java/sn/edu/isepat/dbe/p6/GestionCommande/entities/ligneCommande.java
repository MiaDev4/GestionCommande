package sn.edu.isepat.dbe.p6.GestionCommande.entities;

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

      @Column(name = "idProduit")
      private Long idProduit;

      @Column(nullable = false)
      private Integer quantite;

      @Column(nullable = false, precision = 10, scale = 2)
      private BigDecimal prixUnitaire;

      @ManyToOne
      @JoinColumn(name = "commande_id", nullable = false)
      private Commande commande;
}