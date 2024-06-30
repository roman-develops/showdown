package dev.showdown.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "Voting result DTO")
@Data
@RequiredArgsConstructor
public class VotingResultDto {

    private Long userId;
    private int vote;
}
