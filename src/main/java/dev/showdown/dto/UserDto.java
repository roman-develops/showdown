package dev.showdown.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "User view DTO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String username;

}
