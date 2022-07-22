package br.experian.com.adapters;

import br.experian.com.data.PersonDTO;
import br.experian.com.entity.Person;
import br.experian.com.ports.spi.PersonPersistencePort;
import br.experian.com.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonJpaAdapter implements PersonPersistencePort {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    public PersonJpaAdapter(PersonRepository personRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PersonDTO save(PersonDTO entity) throws Exception {
        Person person = modelMapper.map(entity, Person.class);
        Person personSaved = personRepository.save(person);
        return modelMapper.map(personSaved, PersonDTO.class);
    }

    @Override
    public PersonDTO update(PersonDTO entity, UUID uuid) throws Exception {
        Optional<Person> optional = personRepository.findById(uuid);
        if (optional.isPresent()) {
            Person person = optional.get();
            person.setPhone(entity.getPhone());
            person.setAge(entity.getAge());
            person.setCity(entity.getCity());
            person.setScore(entity.getScore());
            person.setRegion(entity.getRegion());

            Person simulationSaved = personRepository.save(person);
            return modelMapper.map(simulationSaved, PersonDTO.class);
        }
        return null;
    }

    @Override
    public List<PersonDTO> findAll() {
        List<Person> personList = personRepository.findAll();
        Type listType = new TypeToken<List<PersonDTO>>(){}.getType();
        return modelMapper.map(personList, listType);
    }

    @Override
    public PersonDTO findById(UUID entityId) {
        Optional<Person> personOptional = personRepository.findById(entityId);
        if (personOptional.isPresent())
            return modelMapper.map(personOptional.get(), PersonDTO.class);
        return null;
    }

    @Override
    public void deleteById(UUID entityId) {
        personRepository.deleteById(entityId);
    }
}
