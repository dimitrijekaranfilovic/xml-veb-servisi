package rs.vakcinacija.sluzbenici.vakcinacionipunkt.exception;

public class VaccineDoesntExistException extends RuntimeException{
    public VaccineDoesntExistException(String message) {
        super(message);
    }
}
