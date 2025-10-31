package uz.brb.redis_test.exception;

public class JsonConversionException extends RuntimeException {
    public JsonConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}
