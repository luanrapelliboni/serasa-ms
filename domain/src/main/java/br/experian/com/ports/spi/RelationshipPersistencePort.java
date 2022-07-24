package br.experian.com.ports.spi;

import br.experian.com.data.RelationshipDTO;

import java.util.Optional;
import java.util.UUID;

public interface RelationshipPersistencePort extends GenericPersistencePort<RelationshipDTO, UUID> {
    Optional<RelationshipDTO> findByRegion(String region) throws Exception;
}
