package dev.showdown.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Schema(name = "JWT token DTO")
@Data
@Builder
@RequiredArgsConstructor
public class TokenDto {

    private String accessToken;
    private final String tokenType = "Bearer ";

    public TokenDto(String accessToken) {
        this.accessToken = accessToken;
    }

}
