package dev.showdown.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "Game view DTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameViewDto {

    private Long id;

    private TableViewDto table;

    private Integer value;

    private boolean showdown;

}
