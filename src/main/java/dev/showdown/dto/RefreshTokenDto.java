package dev.showdown.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "Refresh token DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenDto {

    private String refreshToken;

}
