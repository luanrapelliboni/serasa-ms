package br.experian.com.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "states")
public class State extends Base {
    private String code;

    @ManyToOne
    @JoinColumn(name = "relationship_uuid", nullable = false)
    private Relationship relationship;
}
