package br.experian.com.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDTO extends BaseDTO {
    private String name;
    private String phone;
    private BigDecimal age;
    private String city;
    private String state;
    private BigDecimal score;
    private String region;

}
