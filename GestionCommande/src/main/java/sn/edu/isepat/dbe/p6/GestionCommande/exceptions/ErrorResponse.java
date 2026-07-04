package sn.edu.isepat.dbe.p6.GestionCommande.exceptions;

import java.time.LocalDateTime;

public record ErrorResponse(
        Integer status,
        String message,
        LocalDateTime timestamp
) {}
