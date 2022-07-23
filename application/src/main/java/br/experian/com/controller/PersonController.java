package br.experian.com.controller;

import br.experian.com.data.PersonDTO;
import br.experian.com.ports.api.PersonServicePort;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

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
    public PersonDTO add(@RequestBody PersonDTO personDTO) throws Exception {
        return personServicePort.save(personDTO);
    }

    @PutMapping("/{uuid}")
    public PersonDTO update(@RequestBody PersonDTO personDTO, @PathVariable UUID uuid) throws Exception{
        return personServicePort.update(personDTO, uuid);
    }

    @GetMapping("/{uuid}")
    public PersonDTO findBy(@PathVariable UUID uuid) {
        return personServicePort.findById(uuid);
    }

    @GetMapping("")
    public List<PersonDTO> getAll() {
        return personServicePort.findAll();
    }

    @DeleteMapping("/{uuid}")
    public void deleteBy(@PathVariable UUID uuid) {
        personServicePort.deleteById(uuid);
    }
}
