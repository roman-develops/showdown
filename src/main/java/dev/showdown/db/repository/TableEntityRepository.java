package dev.showdown.db.repository;

import dev.showdown.db.entity.TableEntity;
import dev.showdown.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TableEntityRepository extends JpaRepository<TableEntity, Long> {

    Optional<TableEntity> getTableEntitiesByLinkIdentifier(String linkIdentifier);

    List<TableEntity> findAllByOwner(UserEntity owner);
}
