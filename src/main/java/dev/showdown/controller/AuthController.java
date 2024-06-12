package dev.showdown.controller;

import dev.showdown.dto.LoginDto;
import dev.showdown.dto.RegisterDto;
import dev.showdown.dto.TokenDto;
import dev.showdown.dto.UserViewDto;
import dev.showdown.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @Operation(tags = { "Authorization" }, summary = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authenticationService.login(loginDto));
    }

    @Operation(tags = { "Authorization" }, summary = "Register new account")
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDto registerDto) {
        UserViewDto userViewDto = authenticationService.register(registerDto);
        return ResponseEntity
                .created(URI.create("/api/users/" + userViewDto.getId().toString()))
                .build();
    }

}
