package rs.vakcinacija.sluzbenici.vakcinacionipunkt.exception;

import rs.vakcinacija.sluzbenici.config.exception.BusinessException;

public class VaccineDoesntExistException extends BusinessException {
    public VaccineDoesntExistException(String message) {
        super(message);
    }
}
