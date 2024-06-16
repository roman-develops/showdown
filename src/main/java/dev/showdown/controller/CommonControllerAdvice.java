package dev.showdown.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<String> responseStatusException(ResponseStatusException ex) {
        return ResponseEntity.badRequest().body(ex.getReason());
    }

    @ExceptionHandler
    public ResponseEntity<String> entityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
