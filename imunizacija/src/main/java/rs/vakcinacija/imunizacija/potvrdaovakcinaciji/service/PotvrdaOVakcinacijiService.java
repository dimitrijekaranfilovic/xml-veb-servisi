package rs.vakcinacija.imunizacija.potvrdaovakcinaciji.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import rs.vakcinacija.imunizacija.potvrdaovakcinaciji.model.KolekcijaPotvrdaOVakcinaciji;

import java.util.UUID;

@Service
public class PotvrdaOVakcinacijiService {
    private final PotvrdaOVakcinacijiClient potvrdaOVakcinacijiClient;

    @Autowired
    public PotvrdaOVakcinacijiService(PotvrdaOVakcinacijiClient potvrdaOVakcinacijiClient) {
        this.potvrdaOVakcinacijiClient = potvrdaOVakcinacijiClient;
    }

    public KolekcijaPotvrdaOVakcinaciji readForClient(String email) {
        return potvrdaOVakcinacijiClient.readForCitizen(email, "");
    }

    public byte[] getPDFRepresentation(UUID id) throws Exception {
        return potvrdaOVakcinacijiClient.stringPdf(id);
    }

    public String getHTMLRepresentation(UUID id) throws Exception {
        return potvrdaOVakcinacijiClient.html(id);
    }
}
