package dev.showdown.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Schema(name = "Refresh token DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenDto {

    private Long userId;
    private String refreshToken;
    private Date createdAt;
    private Date expiresAt;

}
