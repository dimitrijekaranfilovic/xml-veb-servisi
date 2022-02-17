package rs.vakcinacija.sluzbenici.zahtevzasertifikat.exception;

import rs.vakcinacija.sluzbenici.config.exception.BusinessException;

public class CitizenDoesNotHaveTwoDosesException extends BusinessException {
    public CitizenDoesNotHaveTwoDosesException(String message) {
        super(message);
    }
}
