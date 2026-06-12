package sn.edu.isepat.dbe.p6.GestionCommande.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produit {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(nullable = false, unique = true)
     private String nom;

     @Column(nullable = false)
     private BigDecimal prix;

     private Integer stock = 0;

     @ManyToOne
     @JoinColumn(name = "createurId")
     private Utilisateur createur;

}
