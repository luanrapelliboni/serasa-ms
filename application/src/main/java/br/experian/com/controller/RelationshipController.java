package br.experian.com.controller;

import br.experian.com.data.RelationshipDTO;
import br.experian.com.ports.api.RelationshipServicePort;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/relationship")
@SecurityRequirement(name = "Bearer Authentication")
@Api(value = "relationship", tags = {"relationship"})
public class RelationshipController {
    private final RelationshipServicePort relationshipServicePort;

    public RelationshipController(RelationshipServicePort relationshipServicePort) {
        this.relationshipServicePort = relationshipServicePort;
    }

    @PostMapping("")
    public RelationshipDTO add(@RequestBody RelationshipDTO relationshipDTO) throws Exception {
        return relationshipServicePort.save(relationshipDTO);
    }

    @PutMapping("/{uuid}")
    public RelationshipDTO update(@RequestBody RelationshipDTO relationshipDTO, @PathVariable UUID uuid) throws Exception{
        return relationshipServicePort.update(relationshipDTO, uuid);
    }

    @GetMapping("/{uuid}")
    public RelationshipDTO findBy(@PathVariable UUID uuid) {
        return relationshipServicePort.findById(uuid);
    }

    @GetMapping("")
    public List<RelationshipDTO> getAll() {
        return relationshipServicePort.findAll();
    }

    @DeleteMapping("/{uuid}")
    public void deleteBy(@PathVariable UUID uuid) {
        relationshipServicePort.deleteById(uuid);
    }
}
