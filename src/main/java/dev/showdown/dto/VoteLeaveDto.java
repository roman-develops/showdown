package dev.showdown.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "Voting Result DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteLeaveDto {

    private Long userId;
    private boolean isVoted;
}
