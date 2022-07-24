package br.experian.com.data.response;

import br.experian.com.data.BaseDTO;
import br.experian.com.data.StateDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonResponseDTO extends BaseDTO {
    private String name;
    private String phone;
    private BigDecimal age;
    @JsonProperty(value = "score_description")
    private String scoreDescription;
    private List<StateDTO> states;
}
