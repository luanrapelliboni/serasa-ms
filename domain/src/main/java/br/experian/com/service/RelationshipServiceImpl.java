package br.experian.com.service;

import br.experian.com.data.RelationshipDTO;
import br.experian.com.ports.api.RelationshipServicePort;
import br.experian.com.ports.spi.RelationshipPersistencePort;

import java.util.UUID;

public class RelationshipServiceImpl extends GenericServiceImpl<RelationshipDTO, UUID> implements RelationshipServicePort {
    private RelationshipPersistencePort relationshipPersistencePort;

    public RelationshipServiceImpl(RelationshipPersistencePort relationshipPersistencePort) {
        super(relationshipPersistencePort);
        this.relationshipPersistencePort = relationshipPersistencePort;
    }
}
