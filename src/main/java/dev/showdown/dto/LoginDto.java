package dev.showdown.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "Login DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    private String username;
    private String password;

}
