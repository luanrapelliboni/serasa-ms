package br.experian.com.repository;

import br.experian.com.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends AbstractRepository<Person, UUID> {
    // Custom
}
