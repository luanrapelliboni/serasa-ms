package br.experian.com.configuration;

import br.experian.com.adapters.PersonJpaAdapter;
import br.experian.com.ports.spi.RelationshipPersistencePort;
import br.experian.com.ports.spi.ScorePersistencePort;
import br.experian.com.repository.PersonRepository;
import br.experian.com.ports.api.PersonServicePort;
import br.experian.com.ports.spi.PersonPersistencePort;
import br.experian.com.service.PersonServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfig {
    @Bean
    public PersonPersistencePort personPersistencePort(PersonRepository personRepository, ModelMapper modelMapper) {
        return new PersonJpaAdapter(personRepository, modelMapper);
    }

    @Bean
    public PersonServicePort personServicePort(PersonRepository personRepository,
                                               RelationshipPersistencePort relationshipPersistencePort,
                                               ScorePersistencePort scorePersistencePort,
                                               ModelMapper modelMapper) {
        return new PersonServiceImpl(personPersistencePort(personRepository, modelMapper), relationshipPersistencePort, scorePersistencePort);
    }
}
