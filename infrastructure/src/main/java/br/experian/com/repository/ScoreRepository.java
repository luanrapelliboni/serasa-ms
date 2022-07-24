package br.experian.com.repository;

import br.experian.com.entity.Score;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ScoreRepository extends AbstractRepository<Score, UUID> {
    @Query("SELECT s FROM Score s WHERE s.initialRange <= :score  and s.finalRange >= :score")
    Optional<Score> findByRange(@Param("score") BigDecimal score);
}