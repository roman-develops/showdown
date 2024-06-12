package dev.showdown.controller;

import dev.showdown.dto.GameDto;
import dev.showdown.dto.TableDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class GameController {

    // TODO Implement this
    @GetMapping("/table-games/{tableId}")
    public Page<GameDto> getTableGames(@PathVariable Long tableId) {
        return Page.empty();
    }

    // TODO Implement this
    @GetMapping("/games/{gameId}")
    public GameDto getGame(@PathVariable Long gameId) {
        return new GameDto();
    }

    // TODO Implement this
    @PostMapping("/games")
    public ResponseEntity<Void> createUser(@RequestBody GameDto gameDto) {
        return ResponseEntity
                .created(URI.create("/api/games"))
                .build();
    }

}
