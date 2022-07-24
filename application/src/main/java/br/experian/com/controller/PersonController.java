package br.experian.com.controller;

import br.experian.com.data.PersonDTO;
import br.experian.com.data.response.PersonResponseDTO;
import br.experian.com.ports.api.PersonServicePort;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/person")
@SecurityRequirement(name = "Bearer Authentication")
@Api(value = "person", tags = {"person"})
public class PersonController {
    private final PersonServicePort personServicePort;

    public PersonController(PersonServicePort personServicePort) {
        this.personServicePort = personServicePort;
    }

    @PostMapping("")
    public ResponseEntity<PersonDTO> add(@RequestBody PersonDTO personDTO, UriComponentsBuilder builder) throws Exception {
        PersonDTO personSaved = personServicePort.save(personDTO);
        URI location = builder.path("/{uuid}")
                .buildAndExpand(personSaved.getUuid())
                .toUri();
        return ResponseEntity.created(location).body(personSaved);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO personDTO, @PathVariable UUID uuid) throws Exception{
        return ResponseEntity.ok(personServicePort.update(personDTO, uuid));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<PersonResponseDTO> findBy(@PathVariable UUID uuid) throws Exception {
        return ResponseEntity.ok(personServicePort.findByIdCustomResponse(uuid));
    }

    @GetMapping("")
    public ResponseEntity<List<PersonResponseDTO>> getAll() throws Exception {
        return ResponseEntity.ok(personServicePort.findAllCustomResponse());
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteBy(@PathVariable UUID uuid) throws Exception {
        personServicePort.deleteById(uuid);
        return ResponseEntity.noContent().build();
    }
}
