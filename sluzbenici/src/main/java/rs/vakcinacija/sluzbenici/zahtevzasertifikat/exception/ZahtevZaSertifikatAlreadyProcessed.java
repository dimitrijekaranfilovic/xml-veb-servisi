package rs.vakcinacija.sluzbenici.zahtevzasertifikat.exception;

import rs.vakcinacija.sluzbenici.config.exception.BusinessException;

public class ZahtevZaSertifikatAlreadyProcessed extends BusinessException {
    public ZahtevZaSertifikatAlreadyProcessed(String message) {
        super(message);
    }
}
