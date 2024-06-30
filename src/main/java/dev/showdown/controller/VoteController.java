package dev.showdown.controller;

import dev.showdown.dto.GameViewDto;
import dev.showdown.dto.VoteLeaveDto;
import dev.showdown.dto.VoteValueDto;
import dev.showdown.dto.VoteViewDto;
import dev.showdown.service.TableService;
import dev.showdown.service.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class VoteController {

    private final TableService tableService;
    private final VoteService voteService;
    private final SimpMessagingTemplate messagingTemplate;

    // TODO Implement this
    @GetMapping("/game-votes/{gameId}")
    @Operation(tags = {"Vote"}, summary = "Get game votes by game id")
    public Page<GameViewDto> getGameVotes(@PathVariable Long gameId) {
        return Page.empty();
    }

    // TODO Implement this
    @GetMapping("/votes/{voteId}")
    @Operation(tags = {"Vote"}, summary = "Get a vote by id")
    public VoteViewDto getVote(@PathVariable Long voteId) {
        return new VoteViewDto();
    }

    @PreAuthorize("@tableService.isUserTableParticipant(#principal.name, #tableId)")
    @MessageMapping("/table-votes/{tableId}")
    public void leaveVote(@Payload VoteValueDto voteValue, @DestinationVariable String tableId, Principal principal) {
        VoteLeaveDto voteLeaveDto = voteService.saveVoteLeave(tableId, principal.getName(), voteValue.getVoteValue());
        tableService.getTableParticipants(tableId).forEach(userViewDto ->
                messagingTemplate.convertAndSendToUser(userViewDto.getUsername(), "/queue/votes", voteLeaveDto));
    }
}
