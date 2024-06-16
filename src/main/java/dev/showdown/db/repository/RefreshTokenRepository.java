package dev.showdown.db.repository;

import dev.showdown.db.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    boolean existsByValue(String value);

    void deleteByValue(String value);

}
