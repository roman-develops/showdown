package dev.showdown.controller;

import dev.showdown.dto.*;
import dev.showdown.service.AuthService;
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

    private final AuthService authService;

    @Operation(tags = { "Authorization" }, summary = "Login")
    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @Operation(tags = { "Authorization" }, summary = "Register new account")
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDto registerDto) {
        UserDto userDto = authService.register(registerDto);
        return ResponseEntity
                .created(URI.create("/api/users/" + userDto.getId().toString()))
                .build();
    }

    @Operation(tags = { "Authorization" }, summary = "Refresh token")
    @PostMapping("/refresh")
    public TokenDto refreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        return authService.refreshToken(refreshTokenDto);
    }

}
