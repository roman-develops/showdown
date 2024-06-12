package dev.showdown.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
@RequiredArgsConstructor
public class JwtProperties {

    private String secret;
    private Long expiration;
    private String tokenType;

}
