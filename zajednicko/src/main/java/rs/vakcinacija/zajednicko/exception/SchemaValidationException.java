package rs.vakcinacija.zajednicko.exception;

public class SchemaValidationException extends RuntimeException{

    public SchemaValidationException(String message) {
        super(message);
    }
}
