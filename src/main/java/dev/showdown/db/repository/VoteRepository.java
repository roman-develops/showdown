package dev.showdown.db.repository;

import dev.showdown.db.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findAllByGameIdAndUserEntityId(Long gameId, Long userId);

    List<Vote> getVoteByGameId(Long gameId);

}
