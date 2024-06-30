package dev.showdown.db.repository;

import dev.showdown.db.entity.TableEntity;
import dev.showdown.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, String> {

    Optional<TableEntity> getTableEntityById(String id);

    List<TableEntity> findAllByOwner(UserEntity owner);

    @Query("select case " +
            "when count(t) > 0 " +
            "then true " +
            "else false " +
            "end " +
            "from TableEntity t " +
            "where t.id = :tableId and t.owner.username = :username")
    boolean isUserTableOwner(String username, String tableId);

    @Query("select case " +
            "when count(t) > 0 " +
            "then true " +
            "else false " +
            "end " +
            "from TableEntity t " +
            "where t.id = :tableId and (" +
            ":username = t.owner.username or " +
            ":username in (select u.username from TableEntity t join t.participants u where t.id = :tableId))")
    boolean isUserTableOwnerOrParticipant(String username, String tableId);
}
