package dev.showdown.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "token")
@Getter
@Setter
@RequiredArgsConstructor
public class TokenProperties {

    private String secret;
    private Long expiration;
    private String tokenType;

}
