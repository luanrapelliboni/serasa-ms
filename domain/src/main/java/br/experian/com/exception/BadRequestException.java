package br.experian.com.exception;

public class BadRequestException extends ApplicationException {
    private static final long serialVersionUID = -4256421255525787532L;

    public BadRequestException(String message) {
        super(message);
    }
}