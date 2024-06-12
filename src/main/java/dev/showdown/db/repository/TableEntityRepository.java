package dev.showdown.db.repository;

import dev.showdown.db.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableEntityRepository extends JpaRepository<TableEntity, Long> {
}
