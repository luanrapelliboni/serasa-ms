package br.experian.com.exception;

public class BadGatewayException extends ApplicationException {
    private static final long serialVersionUID = 3996682119585104808L;
    public BadGatewayException(String message) {
        super(message);
    }
}
