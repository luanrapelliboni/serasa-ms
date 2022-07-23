package br.experian.com.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
@Table(name = "relationships")
public class Relationship extends Base {

    public Relationship() {
        states = new ArrayList<>();
    }

    private String region;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "relationship")
    private List<State> states;

    public void fillRelatedState() {
        if (states != null && states.size() > 0) {
            states.stream().forEach(s -> s.setRelationship(this));
        }
    }

    public void setStates(List<State> states) {
        this.getStates().clear();
        this.getStates().addAll(states);
    }
}
