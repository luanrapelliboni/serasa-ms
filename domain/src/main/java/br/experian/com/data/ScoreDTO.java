package br.experian.com.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoreDTO extends BaseDTO {
    private String description;

    @JsonProperty(value = "initial_range")
    private BigDecimal initialRange;

    @JsonProperty(value = "final_range")
    private BigDecimal finalRange;
}
