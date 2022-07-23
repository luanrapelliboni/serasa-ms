package br.experian.com.controller;

import br.experian.com.data.ScoreDTO;
import br.experian.com.ports.api.ScoreServicePort;
import br.experian.com.ports.api.ScoreServicePort;
import io.swagger.annotations.Api;
import io.swagger.models.Response;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/score")
@SecurityRequirement(name = "Bearer Authentication")
@Api(value = "score", tags = {"score"})
public class ScoreController {
    private final ScoreServicePort scoreServicePort;

    public ScoreController(ScoreServicePort scoreServicePort) {
        this.scoreServicePort = scoreServicePort;
    }

    @PostMapping("")
    public ResponseEntity<ScoreDTO> add(@RequestBody ScoreDTO scoreDTO, UriComponentsBuilder builder) throws Exception {
        ScoreDTO scoreSaved = scoreServicePort.save(scoreDTO);
        URI location = builder.path("/{uuid}")
                .buildAndExpand(scoreSaved.getUuid())
                .toUri();
        return ResponseEntity.created(location).body(scoreSaved);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ScoreDTO> update(@RequestBody ScoreDTO scoreDTO, @PathVariable UUID uuid) throws Exception {
        return ResponseEntity.ok(scoreServicePort.update(scoreDTO, uuid));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ScoreDTO> findBy(@PathVariable UUID uuid) throws Exception {
        return ResponseEntity.ok(scoreServicePort.findById(uuid));
    }

    @GetMapping("")
    public ResponseEntity<List<ScoreDTO>> getAll() throws Exception {
        return ResponseEntity.ok(scoreServicePort.findAll());
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteBy(@PathVariable UUID uuid) throws Exception {
        scoreServicePort.deleteById(uuid);
        return ResponseEntity.noContent().build();
    }
}
