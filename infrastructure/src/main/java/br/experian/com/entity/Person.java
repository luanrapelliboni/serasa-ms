package br.experian.com.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "persons")
public class Person extends Base {
    private String name;
    private String phone;
    private BigDecimal age;
    private String city;
    private String state;
    private BigDecimal score;
    private String region;
}
