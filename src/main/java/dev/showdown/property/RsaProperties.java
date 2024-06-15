package dev.showdown.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
@ConfigurationProperties(prefix = "rsa")
@Getter
@Setter
@RequiredArgsConstructor
public class RsaProperties {

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

}
