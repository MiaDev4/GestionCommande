package sn.edu.isepat.dbe.p6.GestionCommande.exceptions;

import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ApiException extends RuntimeException{
     private ErrorResponse errorResponse;

     public ApiException (Integer status, String message){
          super("code : "+status+" message : "+message);
          errorResponse = new ErrorResponse(status, message, LocalDateTime.now());
     }
     public ResponseEntity<ErrorResponse> getErrorResponse(){
          return ResponseEntity.status(errorResponse.status()).body(errorResponse);
     }
}
