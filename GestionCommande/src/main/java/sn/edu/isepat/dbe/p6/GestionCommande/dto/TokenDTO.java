package sn.edu.isepat.dbe.p6.GestionCommande.dto;

import java.time.LocalDateTime;

public record TokenDTO(Long id, String valeur, LocalDateTime dateExpiration, boolean revoque) {}
