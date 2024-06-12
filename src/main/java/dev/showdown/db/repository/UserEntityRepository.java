package dev.showdown.db.repository;

import dev.showdown.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUsername(String user);

    Optional<UserEntity> findByUsername(String username);

}
