package dev.showdown.controller;

import dev.showdown.dto.GameDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class GameController {

    // TODO Implement this
    @Operation(tags = { "Game" }, summary = "Get all table games by table id")
    @GetMapping("/table-games/{tableId}")
    public Page<GameDto> getTableGames(@PathVariable Long tableId) {
        return Page.empty();
    }

    // TODO Implement this
    @Operation(tags = { "Game" }, summary = "Get a game by id")
    @GetMapping("/games/{gameId}")
    public GameDto getGame(@PathVariable Long gameId) {
        return new GameDto();
    }

    // TODO Implement this
    @Operation(tags = { "Game" }, summary = "Delete an active game and start new one")
    @PostMapping("/table-games/{tableId}")
    public ResponseEntity<Void> startNewGame(@RequestBody GameDto gameDto, @PathVariable Long tableId) {
        return ResponseEntity
                .created(URI.create("/api/games"))
                .build();
    }

}
