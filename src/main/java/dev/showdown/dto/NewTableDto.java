package dev.showdown.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "New table DTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewTableDto {

    private String name;

    private String votingSystem;
}
