package sn.edu.isepat.dbe.p6.GestionCommande.dto;

import lombok.Getter;
import lombok.Setter;
import sn.edu.isepat.dbe.p6.GestionCommande.entity.Commande.StatutCommande;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CommandeDTO {

      private Long id;
      private LocalDateTime dateCommande;
      private StatutCommande status;
      private Long idClient;
      private List<LigneCommandeDTO> lignes;
      private Double total;
}