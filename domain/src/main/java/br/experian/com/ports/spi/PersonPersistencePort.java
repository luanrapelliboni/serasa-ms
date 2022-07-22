package br.experian.com.ports.spi;

import br.experian.com.data.PersonDTO;

import java.util.UUID;

public interface PersonPersistencePort extends GenericPersistencePort<PersonDTO, UUID> {
    // custom méthods
}
