package rs.vakcinacija.imunizacija.potvrdaovakcinaciji.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.imunizacija.potvrdaovakcinaciji.model.KolekcijaPotvrdaOVakcinaciji;

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
}
