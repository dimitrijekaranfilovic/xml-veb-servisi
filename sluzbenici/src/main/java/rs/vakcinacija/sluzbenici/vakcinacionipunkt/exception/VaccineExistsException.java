package rs.vakcinacija.sluzbenici.vakcinacionipunkt.exception;

import rs.vakcinacija.sluzbenici.config.exception.BusinessException;

public class VaccineExistsException extends BusinessException {

    public VaccineExistsException(String message) {
        super(message);
    }
}
