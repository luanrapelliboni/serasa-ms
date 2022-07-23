package br.experian.com.service;

import br.experian.com.data.ScoreDTO;
import br.experian.com.ports.api.ScoreServicePort;
import br.experian.com.ports.spi.ScorePersistencePort;

import java.util.UUID;

public class ScoreServiceImpl extends GenericServiceImpl<ScoreDTO, UUID> implements ScoreServicePort {
    private ScorePersistencePort scorePersistencePort;

    public ScoreServiceImpl(ScorePersistencePort scorePersistencePort) {
        super(scorePersistencePort);
        this.scorePersistencePort = scorePersistencePort;
    }
}
