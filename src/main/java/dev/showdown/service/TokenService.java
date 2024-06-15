package dev.showdown.service;

import dev.showdown.property.TokenProperties;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class TokenService {

    private final TokenProperties tokenProperties;
    private final JwtEncoder jwtEncoder;

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();

        return jwtEncoder.encode(JwtEncoderParameters.from(
                JwtClaimsSet.builder()
                        .subject(authentication.getName())
                        .issuedAt(now)
                        .expiresAt(now.plusSeconds(tokenProperties.getExpiration()))
                        .build()))
                .getTokenValue();
    }
}
