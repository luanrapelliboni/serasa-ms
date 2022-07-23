package br.experian.com.adapters;

import br.experian.com.data.RelationshipDTO;
import br.experian.com.entity.Relationship;
import br.experian.com.entity.State;
import br.experian.com.ports.spi.RelationshipPersistencePort;
import br.experian.com.repository.RelationshipRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RelationshipJpaAdapter implements RelationshipPersistencePort {
    private final RelationshipRepository relationshipRepository;
    private final ModelMapper modelMapper;

    public RelationshipJpaAdapter(RelationshipRepository relationshipRepository, 
                                  ModelMapper modelMapper) {
        this.relationshipRepository = relationshipRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RelationshipDTO save(RelationshipDTO entity) throws Exception {
        Relationship relationship = modelMapper.map(entity, Relationship.class);
        relationship.fillRelatedState();
        Relationship relationshipSaved = relationshipRepository.save(relationship);
        return modelMapper.map(relationshipSaved, RelationshipDTO.class);
    }

    @Override
    public RelationshipDTO update(RelationshipDTO entity, UUID uuid) throws Exception {
        Optional<Relationship> optional = relationshipRepository.findById(uuid);
        if (optional.isPresent()) {
            Relationship relationship = optional.get();
            relationship.setRegion(entity.getRegion());
            relationship.setStates(modelMapper.map(entity.getStates(), new TypeToken<List<State>>(){}.getType()));
            relationship.fillRelatedState();
            Relationship relationshipSaved = relationshipRepository.save(relationship);
            return modelMapper.map(relationshipSaved, RelationshipDTO.class);
        }
        return null;
    }

    @Override
    public List<RelationshipDTO> findAll() {
        List<Relationship> relationshipList = relationshipRepository.findAll();
        Type listType = new TypeToken<List<RelationshipDTO>>(){}.getType();
        return modelMapper.map(relationshipList, listType);
    }

    @Override
    public RelationshipDTO findById(UUID entityId) {
        Optional<Relationship> relationshipOptional = relationshipRepository.findById(entityId);
        if (relationshipOptional.isPresent())
            return modelMapper.map(relationshipOptional.get(), RelationshipDTO.class);
        return null;
    }

    @Override
    public void deleteById(UUID entityId) {
        relationshipRepository.deleteById(entityId);
    }
}
