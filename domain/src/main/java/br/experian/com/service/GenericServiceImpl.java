package br.experian.com.service;



import br.experian.com.data.BaseDTO;
import br.experian.com.ports.api.GenericServicePort;
import br.experian.com.ports.spi.GenericPersistencePort;

import java.io.Serializable;
import java.util.List;

public class GenericServiceImpl<T extends BaseDTO, ID extends Serializable>
        implements GenericServicePort<T, ID> {

    private final GenericPersistencePort<T, ID> genericPersistencePort;

    public GenericServiceImpl(GenericPersistencePort<T, ID> genericPersistencePort) {
        this.genericPersistencePort = genericPersistencePort;
    }

    @Override
    public T save(T entity) throws Exception {
        return (T) genericPersistencePort.save(entity);
    }

    @Override
    public T update(T entity, ID id) throws Exception {
        return (T) genericPersistencePort.update(entity, id);
    }

    @Override
    public List<T> findAll() throws Exception {
        return genericPersistencePort.findAll();
    }

    @Override
    public T findById(ID entityId) throws Exception {
        return genericPersistencePort.findById(entityId);
    }

    @Override
    public void deleteById(ID entityId) throws Exception {
        genericPersistencePort.deleteById(entityId);
    }

}
