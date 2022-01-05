package rs.vakcinacija.imunizacija.saglasnost.repository;

import org.springframework.stereotype.Component;
import rs.vakcinacija.imunizacija.saglasnost.model.SaglasnostZaSprovodjenjeImunizacije;
import rs.vakcinacija.imunizacija.zajednicko.repository.ExistRepository;

@Component
public class SaglasnostRepository extends ExistRepository<SaglasnostZaSprovodjenjeImunizacije> {

    public SaglasnostRepository() {
        super("saglasnost", SaglasnostZaSprovodjenjeImunizacije.class);
    }
}
