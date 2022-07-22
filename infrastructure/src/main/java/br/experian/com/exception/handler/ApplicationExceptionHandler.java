package br.experian.com.exception.handler;

import br.experian.com.data.exception.CustomFieldErrorDTO;
import br.experian.com.data.exception.ErrorMessageDTO;
import br.experian.com.data.exception.FieldErrorMessageDTO;
import br.experian.com.exception.BadRequestException;
import br.experian.com.exception.NotFoundException;
import br.experian.com.exception.UnprocessibleEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> resourceNotFoundException(NotFoundException ex, WebRequest request) {
        ErrorMessageDTO message = new ErrorMessageDTO(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage()
        );
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessageDTO> badRequestException(BadRequestException ex, WebRequest request) {
        ErrorMessageDTO message = new ErrorMessageDTO(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                ex.getMessage()
        );
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnprocessibleEntityException.class)
    public ResponseEntity<ErrorMessageDTO> unprocessibleEntityException(UnprocessibleEntityException ex, WebRequest request) {
        ErrorMessageDTO message = new ErrorMessageDTO(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                LocalDateTime.now(),
                ex.getMessage()
        );
        return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Object> handleUserMethodFieldErrors(MethodArgumentNotValidException ex, WebRequest request) {
        final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        final List<CustomFieldErrorDTO> customFieldErrors = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            final String field = fieldError.getField();
            final String message = fieldError.getDefaultMessage();
            final CustomFieldErrorDTO customFieldError = CustomFieldErrorDTO.builder()
                    .field(field)
                    .message(message)
                    .build();
            customFieldErrors.add(customFieldError);
        }

        FieldErrorMessageDTO message = new FieldErrorMessageDTO(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                customFieldErrors
        );
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}