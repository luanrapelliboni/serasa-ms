package br.experian.com.service;

import br.experian.com.data.PersonDTO;
import br.experian.com.data.RelationshipDTO;
import br.experian.com.data.ScoreDTO;
import br.experian.com.data.response.PersonResponseDTO;
import br.experian.com.exception.NotFoundException;
import br.experian.com.exception.UnprocessibleEntityException;
import br.experian.com.ports.api.PersonServicePort;
import br.experian.com.ports.spi.PersonPersistencePort;
import br.experian.com.ports.spi.RelationshipPersistencePort;
import br.experian.com.ports.spi.ScorePersistencePort;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PersonServiceImpl extends GenericServiceImpl<PersonDTO, UUID> implements PersonServicePort {

    private PersonPersistencePort personPersistencePort;

    private RelationshipPersistencePort relationshipPersistencePort;

    private ScorePersistencePort scorePersistencePort;

    public PersonServiceImpl(PersonPersistencePort personPersistencePort,
                             RelationshipPersistencePort relationshipPersistencePort,
                             ScorePersistencePort scorePersistencePort) {
        super(personPersistencePort);
        this.personPersistencePort = personPersistencePort;
        this.relationshipPersistencePort = relationshipPersistencePort;
        this.scorePersistencePort = scorePersistencePort;
    }

    @Override
    public PersonDTO save(PersonDTO entity) throws Exception {
        Optional<RelationshipDTO> relationshipOptional = relationshipPersistencePort.findByRegion(entity.getRegion());

        if (entity.getScore().compareTo(new BigDecimal(0)) < 0 ||
                entity.getScore().compareTo(new BigDecimal(1000)) > 0) {
            throw new UnprocessibleEntityException("score must be between 0 and 1000.");
        }

        if (!relationshipOptional.isPresent())
            throw new NotFoundException(String.format("region: '%s' not found!", entity.getRegion()));

        return super.save(entity);
    }

    @Override
    public PersonDTO update(PersonDTO entity, UUID uuid) throws Exception {
        Optional<RelationshipDTO> relationshipOptional = relationshipPersistencePort.findByRegion(entity.getRegion());

        if (entity.getScore().compareTo(new BigDecimal(0)) < 0 ||
                entity.getScore().compareTo(new BigDecimal(1000)) > 0) {
            throw new UnprocessibleEntityException("score must be between 0 and 1000.");
        }

        if (!relationshipOptional.isPresent())
            throw new NotFoundException(String.format("region: '%s' not found!", entity.getRegion()));

        return super.update(entity, uuid);
    }

    @Override
    public PersonResponseDTO findByIdCustomResponse(UUID entityId) throws Exception {
        PersonDTO found = super.findById(entityId);
        return buildResponse(found);
    }

    @Override
    public List<PersonResponseDTO> findAllCustomResponse() throws Exception {
        List<PersonResponseDTO> personResponseList = new ArrayList<>();

        List<PersonDTO> personDTOList = personPersistencePort.findAll();

        for (PersonDTO personDTO: personDTOList) {
            personResponseList.add(buildResponse(personDTO));
        }

        return personResponseList;
    }

    private PersonResponseDTO buildResponse(PersonDTO found) throws Exception {
        Optional<RelationshipDTO> relationshipOptional = relationshipPersistencePort.findByRegion(found.getRegion());

        if (!relationshipOptional.isPresent())
            throw new NotFoundException(String.format("region: '%s' not found!", found.getRegion()));

        Optional<ScoreDTO> scoreDescriptionOptional = scorePersistencePort.findByRange(found.getScore());
        if (!scoreDescriptionOptional.isPresent())
            throw new UnprocessibleEntityException("score is not valid.");

        PersonResponseDTO personResponseDTO = new PersonResponseDTO(
            found.getName(),
            found.getPhone(),
            found.getAge(),
            scoreDescriptionOptional.get().getDescription(),
            relationshipOptional.get().getStates()
        );

        personResponseDTO.setActive(found.getActive());
        personResponseDTO.setCreated(found.getCreated());
        personResponseDTO.setDeleted(found.getDeleted());
        personResponseDTO.setModified(found.getModified());
        personResponseDTO.setUuid(found.getUuid());
        return personResponseDTO;
    }
}
