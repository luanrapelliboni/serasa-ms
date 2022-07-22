package br.experian.com.service;

import br.experian.com.data.PersonDTO;
import br.experian.com.ports.api.PersonServicePort;
import br.experian.com.ports.spi.PersonPersistencePort;

import java.util.UUID;

public class PersonServiceImpl extends GenericServiceImpl<PersonDTO, UUID> implements PersonServicePort {

    private PersonPersistencePort personPersistencePort;

    public PersonServiceImpl(PersonPersistencePort personPersistencePort) {
        super(personPersistencePort);
        this.personPersistencePort = personPersistencePort;
    }
}
