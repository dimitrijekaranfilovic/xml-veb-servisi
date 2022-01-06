package rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.repository;

import org.springframework.stereotype.Component;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model.PotvrdaOVakcinaciji;
import rs.vakcinacija.sluzbenici.repository.SpringExistRepository;

@Component
public class PotvrdaOVakcinacijiRepository extends SpringExistRepository<PotvrdaOVakcinaciji> {
    protected PotvrdaOVakcinacijiRepository() {
        super("potvrda-o-vakcinaciji", PotvrdaOVakcinaciji.class);
    }
}
