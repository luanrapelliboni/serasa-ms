package br.experian.com.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "scores")
public class Score extends Base {
    private String description;

    @Column(name = "initial_range")
    private BigDecimal initialRange;

    @Column(name = "final_range")
    private BigDecimal finalRange;
}
