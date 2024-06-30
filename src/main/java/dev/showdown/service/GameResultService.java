package dev.showdown.service;

import dev.showdown.db.entity.Game;
import dev.showdown.db.entity.Vote;
import dev.showdown.dto.VotingResultDto;
import dev.showdown.dto.VotingResultsDto;
import dev.showdown.mapper.VoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class GameResultService {

    private final VoteService voteService;
    private final GameService gameService;
    private final VoteMapper voteMapper;


    /**
     * Calculates the average rating and compiles a list of voting results for a given table.
     *
     * @param tableId the ID of the table
     * @return a VotingResultsDto containing the average rating and a list of voting results
     */
    public VotingResultsDto showdown(String tableId) {
        Game game = gameService.getGameByTableId(tableId);
        List<Vote> votes = voteService.getAllVoteByGameId(game.getId());
        List<Integer> votesResultValue = votes.stream()
                .map(Vote::getValue)
                .collect(Collectors.toList());

        double averageRating = calculateAverage(votesResultValue);
        List<VotingResultDto> votingResults = votes.stream()
                .map(voteMapper::toDto)
                .collect(Collectors.toList());

        return VotingResultsDto.builder()
                .averageRating(averageRating)
                .votingResults(votingResults)
                .build();
    }

    /**
     * Calculates the average of a list of integers.
     *
     * @param numbers the list of integers to calculate the average of
     * @return the average of the integers in the list
     * @throws IllegalArgumentException if the list is null or empty
     */
    public static double calculateAverage(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("The list of numbers must not be null or empty");
        }
        double sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum / numbers.size();
    }

}
