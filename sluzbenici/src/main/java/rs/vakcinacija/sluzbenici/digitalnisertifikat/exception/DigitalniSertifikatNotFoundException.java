package rs.vakcinacija.sluzbenici.digitalnisertifikat.exception;

import rs.vakcinacija.zajednicko.exception.DocumentNotFoundException;

public class DigitalniSertifikatNotFoundException extends DocumentNotFoundException {
    public DigitalniSertifikatNotFoundException(String message) {
        super(message);
    }
}
