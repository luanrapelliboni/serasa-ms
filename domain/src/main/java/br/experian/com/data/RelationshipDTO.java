package br.experian.com.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class RelationshipDTO extends BaseDTO {
    public RelationshipDTO() {
        states = new ArrayList<>();
    }
    private String region;
    private List<StateDTO> states;
}
