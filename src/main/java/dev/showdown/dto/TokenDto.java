package dev.showdown.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema(name = "JWT token DTO")
@Data
@Builder
public class TokenDto {

    private String accessToken;
    private String refreshToken;

}
