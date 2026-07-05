package sn.edu.isepat.dbe.p6.GestionCommande.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CommandeException extends RuntimeException {
     private final HttpStatus status;

     public static CommandeException notFound(Long id) {
          return new CommandeException("Commande introuvable avec l'id : " + id, HttpStatus.NOT_FOUND);
     }

     public static CommandeException badRequest(String message) {
          return new CommandeException(message, HttpStatus.BAD_REQUEST);
     }

     private CommandeException(String message, HttpStatus status) {
          super(message);
          this.status = status;
     }

     public HttpStatus getStatus() {
          return status;
     }

     @RestControllerAdvice
     public static class Handler {
          @ExceptionHandler(CommandeException.class)
          public ResponseEntity<Map<String, Object>> handle(CommandeException ex) {
               Map<String, Object> body = new HashMap<>();
               body.put("timestamp", LocalDateTime.now().toString());
               body.put("status", ex.getStatus().value());
               body.put("message", ex.getMessage());
               return new ResponseEntity<>(body, ex.getStatus());
          }
     }
}