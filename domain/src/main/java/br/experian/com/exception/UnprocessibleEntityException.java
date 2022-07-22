package br.experian.com.exception;

public class UnprocessibleEntityException extends ApplicationException {
    private static final long serialVersionUID = -4256421255525787532L;

    public UnprocessibleEntityException(String message) {
        super(message);
    }
}
