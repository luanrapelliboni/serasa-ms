package br.experian.com.repository;

import br.experian.com.entity.Score;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScoreRepository extends AbstractRepository<Score, UUID> {
    // Custom
}
