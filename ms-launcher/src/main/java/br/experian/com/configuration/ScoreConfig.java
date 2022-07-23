package br.experian.com.configuration;

import br.experian.com.adapters.ScoreJpaAdapter;
import br.experian.com.ports.api.ScoreServicePort;
import br.experian.com.ports.spi.ScorePersistencePort;
import br.experian.com.repository.ScoreRepository;
import br.experian.com.service.ScoreServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScoreConfig {
    @Bean
    public ScorePersistencePort scorePersistencePort(ScoreRepository scoreRepository, ModelMapper modelMapper) {
        return new ScoreJpaAdapter(scoreRepository, modelMapper);
    }

    @Bean
    public ScoreServicePort scoreServicePort(ScoreRepository scoreRepository, ModelMapper modelMapper) {
        return new ScoreServiceImpl(scorePersistencePort(scoreRepository, modelMapper));
    }
}
