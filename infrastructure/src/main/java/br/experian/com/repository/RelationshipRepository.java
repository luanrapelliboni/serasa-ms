package br.experian.com.repository;

import br.experian.com.entity.Relationship;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RelationshipRepository extends AbstractRepository<Relationship, UUID> {
    Optional<Relationship> findByRegion(String region);
}
