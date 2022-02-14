package rs.vakcinacija.sluzbenici.config.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
