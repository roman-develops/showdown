package dev.showdown.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Schema(name = "Table DTO")
@Data
@Builder
@RequiredArgsConstructor
public class TableDto {
}
