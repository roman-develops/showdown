package dev.showdown.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Schema(name = "Voting results DTO")
@Data
@Builder
public class VotingResultsDto {

    List<VotingResultDto> votingResults;
    double averageRating;

}

