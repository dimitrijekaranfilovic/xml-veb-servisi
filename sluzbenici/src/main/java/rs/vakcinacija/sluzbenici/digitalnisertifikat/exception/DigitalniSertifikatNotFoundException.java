package rs.vakcinacija.sluzbenici.digitalnisertifikat.exception;

import rs.vakcinacija.sluzbenici.shared.exception.NotFoundException;

public class DigitalniSertifikatNotFoundException extends NotFoundException {
    public DigitalniSertifikatNotFoundException(String message) {
        super(message);
    }
}
