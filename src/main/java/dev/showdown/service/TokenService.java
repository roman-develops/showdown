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

@Service
@AllArgsConstructor
public class TokenService {

    private final UserService userService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProperties tokenProperties;
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

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

    public String extractUsername(String token) {
        return jwtDecoder.decode(token).getSubject();
    }

    public boolean isRefreshTokenValid(String refreshToken) {
        try {
            Jwt decoded = jwtDecoder.decode(refreshToken);
            return refreshTokenRepository.existsByValue(decoded.getTokenValue());
        } catch (JwtException ex) {
            return false;
        }
    }
}
