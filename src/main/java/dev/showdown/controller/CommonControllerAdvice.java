package dev.showdown.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<String> ResponseStatusException(ResponseStatusException ex) {
        return ResponseEntity.badRequest().body(ex.getReason());
    }

}
