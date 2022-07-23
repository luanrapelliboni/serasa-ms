package br.experian.com.adapters;

import br.experian.com.data.ScoreDTO;
import br.experian.com.entity.Score;
import br.experian.com.exception.NotFoundException;
import br.experian.com.ports.spi.ScorePersistencePort;
import br.experian.com.repository.ScoreRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScoreJpaAdapter implements ScorePersistencePort {
    private final ScoreRepository scoreRepository;
    private final ModelMapper modelMapper;

    public ScoreJpaAdapter(ScoreRepository scoreRepository,
                           ModelMapper modelMapper) {
        this.scoreRepository = scoreRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ScoreDTO save(ScoreDTO entity) throws Exception {
        Score score = modelMapper.map(entity, Score.class);
        Score scoreSaved = scoreRepository.save(score);
        return modelMapper.map(scoreSaved, ScoreDTO.class);
    }

    @Override
    public ScoreDTO update(ScoreDTO entity, UUID uuid) throws Exception {
        Optional<Score> optional = scoreRepository.findById(uuid);
        if (optional.isPresent()) {
            Score score = optional.get();
            score.setDescription(entity.getDescription());
            score.setInitialRange(entity.getInitialRange());
            score.setFinalRange(entity.getFinalRange());
            Score scoreSaved = scoreRepository.save(score);
            return modelMapper.map(scoreSaved, ScoreDTO.class);
        }
        else
            throw new NotFoundException("score not found");
    }

    @Override
    public List<ScoreDTO> findAll() throws Exception {
        List<Score> scoreList = scoreRepository.findAll();
        Type listType = new TypeToken<List<ScoreDTO>>(){}.getType();
        return modelMapper.map(scoreList, listType);
    }

    @Override
    public ScoreDTO findById(UUID entityId) throws Exception {
        Optional<Score> scoreOptional = scoreRepository.findById(entityId);
        if (scoreOptional.isPresent())
            return modelMapper.map(scoreOptional.get(), ScoreDTO.class);
        else
            throw new NotFoundException("score not found");
    }

    @Override
    public void deleteById(UUID entityId) throws Exception {
        Optional<Score> scoreOptional = scoreRepository.findById(entityId);
        if (scoreOptional.isPresent())
            scoreRepository.deleteById(entityId);
        else
            throw new NotFoundException("score not found");
    }
}
