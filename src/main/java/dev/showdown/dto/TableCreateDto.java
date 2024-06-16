package dev.showdown.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "Table create DTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableCreateDto {

    private String name;

    private String votingSystem;
}
