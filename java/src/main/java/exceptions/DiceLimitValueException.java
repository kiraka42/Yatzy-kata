package exceptions;

public class DiceLimitValueException extends RuntimeException {
    public DiceLimitValueException(String message) {
        super(message);
    }
}