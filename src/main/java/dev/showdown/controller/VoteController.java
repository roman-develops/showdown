package dev.showdown.controller;

import dev.showdown.dto.GameDto;
import dev.showdown.dto.VoteDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class VoteController {

    // TODO Implement this
    @GetMapping("/game-votes/{gameId}")
    public Page<GameDto> getGameVotes(@PathVariable Long gameId) {
        return Page.empty();
    }

    // TODO Implement this
    @GetMapping("/votes/{voteId}")
    public VoteDto getVote(@PathVariable Long voteId) {
        return new VoteDto();
    }

    // TODO Implement this
    @PostMapping("/votes")
    public ResponseEntity<Void> createUser(@RequestBody VoteDto voteDto) {
        return ResponseEntity
                .created(URI.create("/api/votes"))
                .build();
    }

}
