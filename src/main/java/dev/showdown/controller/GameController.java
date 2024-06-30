package dev.showdown.controller;

import dev.showdown.dto.GameViewDto;
import dev.showdown.service.GameService;
import dev.showdown.service.TableService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;
    private final TableService tableService;

    private final SimpMessagingTemplate messagingTemplate;

    // TODO Implement this
    @Operation(tags = { "Game" }, summary = "Get all table games by table id")
    @GetMapping("/table-games/{tableId}")
    public Page<GameViewDto> getTableGames(@PathVariable Long tableId) {
        return Page.empty();
    }

    // TODO Implement this
    @Operation(tags = { "Game" }, summary = "Get a game by id")
    @GetMapping("/games/{gameId}")
    public GameViewDto getGame(@PathVariable Long gameId) {
        return new GameViewDto();
    }

    // todo catch org.springframework.security.authorization.AuthorizationDeniedException: Access Denied
    @PreAuthorize("@tableService.isUserTableOwner(#principal.name, #tableId)")
    @MessageMapping("/tables/{tableId}/create-game")
    public void startNewGame(@DestinationVariable String tableId, Principal principal) {
        GameViewDto newGameViewDto = gameService.deleteAllTableGamesAndCreateNewGame(tableId);
        tableService.getTableParticipants(tableId).forEach(userViewDto ->
                messagingTemplate.convertAndSendToUser(userViewDto.getUsername(), "/queue/games", newGameViewDto));
    }

}
