package dev.showdown.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "Game create DTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameCreateDto {

    private String tableId;

}
