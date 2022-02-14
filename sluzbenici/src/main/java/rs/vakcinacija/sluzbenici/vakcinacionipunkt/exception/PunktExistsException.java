package rs.vakcinacija.sluzbenici.vakcinacionipunkt.exception;

import rs.vakcinacija.sluzbenici.config.exception.BusinessException;

public class PunktExistsException extends BusinessException {
    public PunktExistsException(String message) {
        super(message);
    }
}
