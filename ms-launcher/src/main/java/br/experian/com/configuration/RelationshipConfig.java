package br.experian.com.configuration;

import br.experian.com.adapters.RelationshipJpaAdapter;
import br.experian.com.ports.api.RelationshipServicePort;
import br.experian.com.ports.spi.RelationshipPersistencePort;
import br.experian.com.repository.RelationshipRepository;
import br.experian.com.service.RelationshipServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RelationshipConfig {
    @Bean
    public RelationshipPersistencePort relationshipPersistencePort(RelationshipRepository relationshipRepository, ModelMapper modelMapper) {
        return new RelationshipJpaAdapter(relationshipRepository, modelMapper);
    }

    @Bean
    public RelationshipServicePort relationshipServicePort(RelationshipRepository relationshipRepository, ModelMapper modelMapper) {
        return new RelationshipServiceImpl(relationshipPersistencePort(relationshipRepository, modelMapper));
    }
}
