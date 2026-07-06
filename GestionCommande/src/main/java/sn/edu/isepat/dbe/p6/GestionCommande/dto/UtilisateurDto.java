package sn.edu.isepat.dbe.p6.GestionCommande.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sn.edu.isepat.dbe.p6.GestionCommande.entities.Role;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDto {
    private long id;
    private String nomUtilisateur;
    private String email;
    private String motPasse;
    private boolean etatCompte;
    private Set<Role> roles; 
}
