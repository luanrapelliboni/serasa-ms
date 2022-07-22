package br.experian.com.exception;

public abstract class ApplicationException extends Exception {
    private static final long serialVersionUID = 2251915782083594725L;

    public ApplicationException(String message) {
        this(message, (Throwable)null);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}

