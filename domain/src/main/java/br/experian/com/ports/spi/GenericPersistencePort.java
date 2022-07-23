package br.experian.com.ports.spi;

import br.experian.com.data.BaseDTO;

import java.io.Serializable;
import java.util.List;

public interface GenericPersistencePort<T extends BaseDTO, ID extends Serializable>  {
    T save(T entity) throws Exception;
    T update(T entity, ID uuid) throws Exception;
    List<T> findAll() throws Exception;
    T findById(ID entityId) throws Exception;
    void deleteById(ID entityId) throws Exception;
}
