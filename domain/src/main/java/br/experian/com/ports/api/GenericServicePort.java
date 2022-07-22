package br.experian.com.ports.api;

import br.experian.com.data.BaseDTO;

import java.io.Serializable;
import java.util.List;

public interface GenericServicePort<T extends BaseDTO, ID extends Serializable>  {
    T save(T entity) throws Exception;
    T update(T entity, ID id) throws Exception;
    List<T> findAll();
    T findById(ID entityId);
    void deleteById(ID entityId);
}
