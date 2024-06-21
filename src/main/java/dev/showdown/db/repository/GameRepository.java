package dev.showdown.db.repository;

import dev.showdown.db.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    void deleteAllByTableId(String tableId);

}
