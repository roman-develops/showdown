package dev.showdown.service;

import dev.showdown.db.entity.RefreshToken;
import dev.showdown.db.repository.RefreshTokenRepository;
import dev.showdown.dto.TokenDto;
import dev.showdown.property.TokenProperties;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * This class is responsible for handling operations
 * related to JWT (JSON Web Token) generation, validation, and extraction.
 */
 @Service
@AllArgsConstructor
public class TokenService {

    private final String BEARER_PREFIX = "Bearer ";

    private final UserService userService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProperties tokenProperties;
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    /**
     * Generates a DTO for the authenticated user.
     *
     * @param authentication The authentication object containing the user's details.
     * @return A `TokenDto` containing the access and refresh tokens.
     */
    public TokenDto generateTokenDto(Authentication authentication) {
        String refreshToken = generateRefreshToken(authentication);

        refreshTokenRepository.saveAndFlush(RefreshToken.builder()
                        .value(refreshToken)
                        .user(userService.getUser(authentication.getName()))
                .build());

        return TokenDto.builder()
                .accessToken(generateAccessToken(authentication))
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * Generates an access token for the authenticated user.
     *
     * @param authentication The authentication object containing the user's details.
     * @return A string representing the access token.
     */
    public String generateAccessToken(Authentication authentication) {
        Instant now = Instant.now();

        return jwtEncoder.encode(JwtEncoderParameters.from(
                JwtClaimsSet.builder()
                        .subject(authentication.getName())
                        .issuedAt(now)
                        .expiresAt(now.plus(tokenProperties.getAccessTokenExpirationMinutes(), ChronoUnit.MINUTES))
                        .build()))
                .getTokenValue();
    }

    /**
     * Generates a refresh token for the authenticated user.
     *
     * @param authentication The authentication object containing the user's details.
     * @return A string representing the refresh token.
     */
    public String generateRefreshToken(Authentication authentication) {
        Instant now = Instant.now();

        return jwtEncoder.encode(JwtEncoderParameters.from(
                        JwtClaimsSet.builder()
                                .subject(authentication.getName())
                                .issuedAt(now)
                                .expiresAt(now.plus(tokenProperties.getRefreshTokenExpirationMinutes(), ChronoUnit.MINUTES))
                                .build()))
                .getTokenValue();
    }

    /**
     * Extracts the username from the provided JWT.
     *
     * @param token The JWT from which the username is to be extracted.
     * @return An Optional containing the username if extraction is successful, or an empty Optional if not.
     */
    public Optional<String> extractUsername(String token) {
        try {
            return Optional.of(jwtDecoder.decode(token).getSubject());
        } catch (JwtException ex) {
            return Optional.empty();
        }
    }

    /**
     * Extracts the token from the provided Bearer string.
     *
     * @param bearerToken The Bearer string from which the token is to be extracted.
     * @return An Optional containing the token if extraction is successful, or an empty Optional if not.
     */
    public Optional<String> extractTokenFromBearerString(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) {
            return Optional.of(bearerToken.substring(BEARER_PREFIX.length()));
        }
        return Optional.empty();
    }

    /**
     * Checks if the provided JWT is valid.
     *
     * @param token The JWT to be validated.
     * @return A boolean indicating whether the token is valid or not.
     */
    public boolean isValid(String token) {
        try {
            Jwt decoded = jwtDecoder.decode(token);
            return true;
        } catch (JwtException ex) {
            return false;
        }
    }

    /**
     * Checks if the provided refresh token is valid.
     *
     * @param refreshToken The refresh token to be validated.
     * @return A boolean indicating whether the refresh token is valid or not.
     */
    public boolean isRefreshTokenValid(String refreshToken) {
        try {
            Jwt decoded = jwtDecoder.decode(refreshToken);
            return refreshTokenRepository.existsByValue(decoded.getTokenValue());
        } catch (JwtException ex) {
            return false;
        }
    }
}
