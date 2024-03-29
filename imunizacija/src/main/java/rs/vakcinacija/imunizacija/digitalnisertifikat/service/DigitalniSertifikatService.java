package rs.vakcinacija.imunizacija.digitalnisertifikat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.imunizacija.digitalnisertifikat.model.KolekcijaDigitalnihSertifikata;

import java.util.UUID;


@Service
public class DigitalniSertifikatService {
    private final DigitalniSertifikatClient digitalniSertifikatClient;

    @Autowired
    public DigitalniSertifikatService(DigitalniSertifikatClient digitalniSertifikatClient) {
        this.digitalniSertifikatClient = digitalniSertifikatClient;
    }

    public KolekcijaDigitalnihSertifikata readForClient(String email) {
        return digitalniSertifikatClient.read(email, null);
    }

    public byte[] getPDFRepresentation(UUID id) throws Exception {
        return this.digitalniSertifikatClient.stringPdf(id);
    }

    public String getHTMLRepresentation(UUID id) throws Exception {
        return this.digitalniSertifikatClient.html(id);
    }
}
