package sn.edu.isepat.dbe.p6.GestionCommande.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class ClientEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long idClient;
    @Column(nullable = false)
    private String nom;
    @Column(unique = true, nullable = false)
    private String email;
    @OneToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
    
}
