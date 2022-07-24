package br.experian.com;

import br.experian.com.data.PersonDTO;
import br.experian.com.data.RelationshipDTO;
import br.experian.com.data.StateDTO;
import br.experian.com.exception.NotFoundException;
import br.experian.com.exception.UnprocessibleEntityException;
import br.experian.com.ports.spi.RelationshipPersistencePort;
import br.experian.com.service.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class PersonServiceImplTest {
    @Mock
    RelationshipPersistencePort relationshipPersistencePort;

    @InjectMocks
    PersonServiceImpl personServicePort;

    @Test
    void given_negative_score_on_save_must_be_thrown_exception() throws Exception {
        Mockito.when(relationshipPersistencePort.findByRegion(Mockito.any())).thenReturn(
            Optional.ofNullable(
                new RelationshipDTO("Sul",
                    Arrays.asList(
                        new StateDTO("SC"),
                        new StateDTO("PR"),
                        new StateDTO("RS")
                    )
                )
            )
        );
        PersonDTO personDTO = buildPersonDTO(-50, "Sul");
        Exception exception = assertThrows(UnprocessibleEntityException.class, () -> {
            personServicePort.save(personDTO);
        });
        String expectedMessage = "score must be between 0 and 1000.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void given_score_greather_than_1000_on_save_must_be_thrown_exception() throws Exception {
        Mockito.when(relationshipPersistencePort.findByRegion(Mockito.any())).thenReturn(
                Optional.ofNullable(
                        new RelationshipDTO("Sul",
                                Arrays.asList(
                                        new StateDTO("SC"),
                                        new StateDTO("PR"),
                                        new StateDTO("RS")
                                )
                        )
                )
        );
        PersonDTO personDTO = buildPersonDTO(1001, "Sul");
        Exception exception = assertThrows(UnprocessibleEntityException.class, () -> {
            personServicePort.save(personDTO);
        });
        String expectedMessage = "score must be between 0 and 1000.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void given_invalid_region_on_save_must_be_thrown_exception() throws Exception {
        Mockito.when(relationshipPersistencePort.findByRegion(Mockito.any())).thenReturn(
            Optional.ofNullable(null)
        );
        PersonDTO personDTO = buildPersonDTO(50,"myregion");
        Exception exception = assertThrows(NotFoundException.class, () -> {
            personServicePort.save(personDTO);
        });
        String expectedMessage = "region: 'myregion' not found!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    private PersonDTO buildPersonDTO(Integer score, String region) {
        return new PersonDTO(
            "John",
            "19 99999-9999",
            new BigDecimal(30),
            "Canela",
            "RS",
            new BigDecimal(score),
            region
        );
    }
}
