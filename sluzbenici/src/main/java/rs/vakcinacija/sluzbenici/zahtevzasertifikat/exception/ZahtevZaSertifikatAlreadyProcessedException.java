package rs.vakcinacija.sluzbenici.zahtevzasertifikat.exception;

import rs.vakcinacija.sluzbenici.config.exception.BusinessException;

public class ZahtevZaSertifikatAlreadyProcessedException extends BusinessException {
    public ZahtevZaSertifikatAlreadyProcessedException(String message) {
        super(message);
    }
}
