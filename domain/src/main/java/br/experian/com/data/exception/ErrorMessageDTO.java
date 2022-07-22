package br.experian.com.data.exception;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ErrorMessageDTO {
    private int statusCode;
    private String timestamp;
    private String message;
    public ErrorMessageDTO(int statusCode, LocalDateTime timestamp, String message) {
        this.statusCode = statusCode;
        this.timestamp = timestamp.toString();
        this.message = message;
    }
}