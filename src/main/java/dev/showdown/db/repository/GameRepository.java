package dev.showdown.db.repository;

import dev.showdown.db.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    void deleteAllByTableId(String tableId);

    @Query("select g from Game g where g.table.id = :tableId")
    Optional<Game> findGameByTableId(String tableId);
}
