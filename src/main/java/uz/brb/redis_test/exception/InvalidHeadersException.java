package uz.brb.redis_test.exception;

public class InvalidHeadersException extends RuntimeException {
    public InvalidHeadersException(String message) {
        super(message);
    }

    public InvalidHeadersException(String message, Throwable cause) {
        super(message, cause);
    }
}