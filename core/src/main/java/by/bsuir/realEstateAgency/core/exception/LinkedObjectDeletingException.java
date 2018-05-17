package by.bsuir.realEstateAgency.core.exception;

public class LinkedObjectDeletingException extends RuntimeException {
    public LinkedObjectDeletingException() {
    }

    public LinkedObjectDeletingException(String message) {
        super(message);
    }

    public LinkedObjectDeletingException(String message, Throwable cause) {
        super(message, cause);
    }

    public LinkedObjectDeletingException(Throwable cause) {
        super(cause);
    }

    public LinkedObjectDeletingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
