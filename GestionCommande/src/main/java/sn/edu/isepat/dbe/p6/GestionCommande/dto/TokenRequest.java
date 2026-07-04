package sn.edu.isepat.dbe.p6.GestionCommande.dto;

import java.time.LocalDateTime;

public record TokenRequest(String valeur, LocalDateTime dateExpiration, Long utilisateurId) {}
