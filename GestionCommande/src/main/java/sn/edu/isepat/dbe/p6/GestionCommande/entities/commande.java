package sn.edu.isepat.dbe.p6.GestionCommande.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "commandes")
@Getter
@Setter
public class Commande {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      @Column(nullable = false)
      private LocalDateTime dateCommande;

      @Enumerated(EnumType.STRING)
      @Column(nullable = false)
      private StatutCommande status;

      @ManyToOne
      @JoinColumn(name = "idClient", nullable = false)
      private Client client;

      @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
      private List<LigneCommande> lignes = new ArrayList<>();

      public enum StatutCommande {
            CREATED, VALIDATED, CANCELLED
      }
}