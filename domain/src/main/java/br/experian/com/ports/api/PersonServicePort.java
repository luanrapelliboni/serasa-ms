package br.experian.com.ports.api;

import br.experian.com.data.PersonDTO;
import br.experian.com.data.response.PersonResponseDTO;

import java.util.List;
import java.util.UUID;

public interface PersonServicePort extends GenericServicePort<PersonDTO, UUID> {
     PersonResponseDTO findByIdCustomResponse(UUID entityId) throws Exception;
     List<PersonResponseDTO> findAllCustomResponse() throws Exception;
}
