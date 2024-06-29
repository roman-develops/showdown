package dev.showdown.controller;

import dev.showdown.dto.TokenDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@ControllerAdvice
@RequiredArgsConstructor
public class CommonControllerAdvice {

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(AuthorizationDeniedException ex, Principal principal) {
        return "Access denied!";
    }

    @ExceptionHandler
    public ResponseEntity<String> responseStatusException(ResponseStatusException ex) {
        return ResponseEntity.badRequest().body(ex.getReason());
    }

    @ExceptionHandler
    public ResponseEntity<String> entityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
