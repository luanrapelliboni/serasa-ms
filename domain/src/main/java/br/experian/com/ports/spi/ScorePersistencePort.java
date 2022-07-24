package br.experian.com.ports.spi;

import br.experian.com.data.ScoreDTO;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface ScorePersistencePort extends GenericPersistencePort<ScoreDTO, UUID> {
    Optional<ScoreDTO> findByRange(BigDecimal score) throws Exception;

}
