package rs.vakcinacija.sluzbenici.vakcinacionipunkt.exception;

public class VaccineExistsException extends RuntimeException{

    public VaccineExistsException(String message) {
        super(message);
    }
}
