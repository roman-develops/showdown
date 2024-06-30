package dev.showdown.service;

import dev.showdown.db.entity.Game;
import dev.showdown.db.entity.UserEntity;
import dev.showdown.db.entity.Vote;
import dev.showdown.db.repository.VoteRepository;
import dev.showdown.dto.VoteLeaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final UserService userService;
    private final GameService gameService;

    /**
     * Retrieves all votes associated with a specific game ID.
     *
     * @param gameId the ID of the game
     * @return a list of votes associated with the specified game ID
     */
    public List<Vote> getAllVoteByGameId(Long gameId) {
        return voteRepository.getVoteByGameId(gameId);
    }

    /**
     * Saves or deletes a vote for a specific user and table. If the vote value is null and a vote exists,
     * the vote is deleted. Otherwise, the vote is saved or updated.
     *
     * @param tableId   the ID of the table
     * @param userName  the username of the user leaving the vote
     * @param voteValue the value of the vote (can be null to indicate deletion)
     * @return a VoteLeaveDto containing the user ID and a flag indicating whether the vote was left
     */
    public VoteLeaveDto saveVoteLeave(String tableId, String userName, Integer voteValue) {
        UserEntity user = userService.getUser(userName);
        Game game = gameService.getGameByTableId(tableId);
        Optional<Vote> vote = voteRepository.findAllByGameIdAndUserEntityId(game.getId(), user.getId());

        if (vote.isPresent() && voteValue == null) {
            voteRepository.delete(vote.get());
            return new VoteLeaveDto(user.getId(), false);
        }
        Vote voteEntity = vote.orElseGet(Vote::new);
        voteEntity.setGame(game);
        voteEntity.setUserEntity(user);
        voteEntity.setValue(voteValue);

        voteRepository.save(voteEntity);
        return new VoteLeaveDto(user.getId(), true);
    }
}
