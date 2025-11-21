package com.cryptotracker.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Map.of("code", ex.getCode(), "message", ex.getMessage()));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAll(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(java.util.Map.of("code","ERR_INTERNAL","message","Erro interno"));
    }
}
