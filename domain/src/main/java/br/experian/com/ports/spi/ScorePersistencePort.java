package br.experian.com.ports.spi;

import br.experian.com.data.ScoreDTO;

import java.util.UUID;

public interface ScorePersistencePort extends GenericPersistencePort<ScoreDTO, UUID> {
    // custom méthods
}
