package rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.repository;

import org.springframework.stereotype.Component;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model.PotvrdaOVakcinaciji;
import rs.vakcinacija.sluzbenici.zajednicko.repository.ExistRepository;

@Component
public class PotvrdaOVakcinacijiRepository extends ExistRepository<PotvrdaOVakcinaciji> {
    protected PotvrdaOVakcinacijiRepository() {
        super("potvrda-o-vakcinaciji", PotvrdaOVakcinaciji.class);
    }
}
