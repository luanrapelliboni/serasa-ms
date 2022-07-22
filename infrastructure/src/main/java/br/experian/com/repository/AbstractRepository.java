package br.experian.com.repository;

import br.experian.com.entity.Base;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface AbstractRepository<T extends Base, ID extends Serializable> extends JpaRepository<T, ID>,
        JpaSpecificationExecutor<T> {

    Page<T> findAll(Pageable pageable);

    @Override
    Page<T> findAll(Specification<T> specification, Pageable pageable);

    @Query("select t from #{#entityName} t where t.uuid = ?1")
    Optional<T> findById(ID id);

    @Override
    Optional<T> findOne(Specification<T> specification);
}
