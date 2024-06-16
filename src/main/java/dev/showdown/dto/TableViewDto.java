package dev.showdown.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "Table DTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableViewDto {

    private String linkIdentifier;

    private String name;

    private String votingSystem;

}
