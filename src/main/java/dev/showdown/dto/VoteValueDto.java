package dev.showdown.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "Voting value DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteValueDto {

    private Integer voteValue;
}
