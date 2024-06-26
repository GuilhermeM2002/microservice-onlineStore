package br.com.onlineStore.catalogms.core.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAPI {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(PriceInvalidException.class)
    public ResponseEntity<String> handlePriceInvalid(){
        return ResponseEntity.badRequest().body("Price should be greater than zero");
    }
}
