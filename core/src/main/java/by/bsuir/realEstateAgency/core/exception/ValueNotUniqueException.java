package by.bsuir.realEstateAgency.core.exception;

public class ValueNotUniqueException extends RuntimeException {
    public ValueNotUniqueException() {
    }

    public ValueNotUniqueException(String message) {
        super(message);
    }

    public ValueNotUniqueException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValueNotUniqueException(Throwable cause) {
        super(cause);
    }

    public ValueNotUniqueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
