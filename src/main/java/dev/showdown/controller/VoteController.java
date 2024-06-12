package dev.showdown.controller;

import dev.showdown.dto.GameDto;
import dev.showdown.dto.VoteLeaveDto;
import dev.showdown.dto.VoteViewDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class VoteController {

    // TODO Implement this
    @GetMapping("/game-votes/{gameId}")
    @Operation(tags = { "Vote" }, summary = "Get game votes by game id")
    public Page<GameDto> getGameVotes(@PathVariable Long gameId) {
        return Page.empty();
    }

    // TODO Implement this
    @GetMapping("/votes/{voteId}")
    @Operation(tags = { "Vote" }, summary = "Get a vote by id")
    public VoteViewDto getVote(@PathVariable Long voteId) {
        return new VoteViewDto();
    }

    // TODO Implement this
    @Operation(tags = { "Vote" }, summary = "Leave a vote for a game by game id")
    @PostMapping("/game-votes/{gameId}")
    public ResponseEntity<Void> leaveVote(@RequestBody VoteLeaveDto voteLeaveDto, @PathVariable Long gameId) {
        return ResponseEntity
                .created(URI.create("/api/votes"))
                .build();
    }

}
