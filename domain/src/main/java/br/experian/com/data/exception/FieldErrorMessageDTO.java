package br.experian.com.data.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class FieldErrorMessageDTO extends ErrorMessageDTO {
    private List<CustomFieldErrorDTO> errors;

    public FieldErrorMessageDTO(int statusCode, LocalDateTime timestamp, List<CustomFieldErrorDTO> errors) {
        super(statusCode, timestamp, "Request validation errors were found.");
        this.errors = errors;
    }
}