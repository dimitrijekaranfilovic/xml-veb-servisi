package rs.vakcinacija.sluzbenici.zahtevzasertifikat.exception;

import rs.vakcinacija.sluzbenici.config.exception.BusinessException;

public class CitizenHasNoVaccinationCertificateException extends BusinessException {
    public CitizenHasNoVaccinationCertificateException(String message) {
        super(message);
    }
}
