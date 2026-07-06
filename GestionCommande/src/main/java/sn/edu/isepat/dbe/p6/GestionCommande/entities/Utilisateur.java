package sn.edu.isepat.dbe.p6.GestionCommande.entities;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.HashSet;
import java.util.Set;

import lombok.Setter;

@Entity
@Getter
@Setter
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String nomUtilisateur;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String motPasse;

    private boolean etatCompte;
    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "utilisateur_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
