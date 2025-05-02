package dev.aetheris.exception;

public class AetherisRuntimeException extends RuntimeException {

    public AetherisRuntimeException(String message) {
        super(message);
    }

    public AetherisRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AetherisRuntimeException(Throwable cause) {
        super(cause);
    }

    public AetherisRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
