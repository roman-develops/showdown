package dev.showdown.service;

import dev.showdown.db.entity.Game;
import dev.showdown.db.repository.GameRepository;
import dev.showdown.dto.GameViewDto;
import dev.showdown.mapper.GameMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class GameService {

    private final TableService tableService;
    private final GameMapper gameMapper;
    private final GameRepository gameRepository;

    /**
     * Deletes all games associated with a specific table and creates a new game for that table.
     *
     * @param tableId The ID of the table for which games are to be deleted and a new game is to be created.
     * @return A DTO representing the newly created game.
     */
    public GameViewDto deleteAllTableGamesAndCreateNewGame(String tableId) {
        gameRepository.deleteAllByTableId(tableId);

        Game newGame = gameRepository.save(Game.builder()
                .table(tableService.getTable(tableId))
                .build());
        return gameMapper.toGameDto(newGame);
    }
}
