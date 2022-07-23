package br.experian.com.controller;

import br.experian.com.data.RelationshipDTO;
import br.experian.com.ports.api.RelationshipServicePort;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<RelationshipDTO> add(@RequestBody RelationshipDTO relationshipDTO, UriComponentsBuilder builder) throws Exception {
        RelationshipDTO relationshipSaved = relationshipServicePort.save(relationshipDTO);
        URI location = builder.path("/{uuid}")
                .buildAndExpand(relationshipSaved.getUuid())
                .toUri();
        return ResponseEntity.created(location).body(relationshipSaved);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<RelationshipDTO> update(@RequestBody RelationshipDTO relationshipDTO, @PathVariable UUID uuid) throws Exception{
        return ResponseEntity.ok(relationshipServicePort.update(relationshipDTO, uuid));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<RelationshipDTO> findBy(@PathVariable UUID uuid) throws Exception {
        return ResponseEntity.ok(relationshipServicePort.findById(uuid));
    }

    @GetMapping("")
    public ResponseEntity<List<RelationshipDTO>> getAll() throws Exception {
        return ResponseEntity.ok(relationshipServicePort.findAll());
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteBy(@PathVariable UUID uuid) throws Exception {
        relationshipServicePort.deleteById(uuid);
        return ResponseEntity.noContent().build();
    }
}
