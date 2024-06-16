package dev.showdown.controller;

import dev.showdown.dto.*;
import dev.showdown.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(tags = { "Authorization" }, summary = "Authenticate a user and return a token")
    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @Operation(tags = { "Authorization" }, summary = "Register a new user account and return the user's details")
    @PostMapping("/register")
    public ResponseEntity<UserViewDto> register(@RequestBody RegisterDto registerDto) {
        UserViewDto newUser = authService.register(registerDto);
        return ResponseEntity
                .created(URI.create("/api/users/" + newUser.getId().toString()))
                .body(newUser);
    }

    @Operation(tags = { "Authorization" }, summary = "Refresh the authentication token for a user")
    @PostMapping("/refresh")
    public TokenDto refreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        return authService.refreshToken(refreshTokenDto);
    }

    @Operation(tags = { "Authorization" }, summary = "Logout a user and invalidate their refresh token")
    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody RefreshTokenDto refreshTokenDto) {
        authService.logout(refreshTokenDto);
        return ResponseEntity.noContent().build();
    }

}
