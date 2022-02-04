package rs.vakcinacija.imunizacija.authentication.exception;

public class UserAllreadyExistsException extends RuntimeException {
    public  UserAllreadyExistsException(String message) { super(message); }
}
