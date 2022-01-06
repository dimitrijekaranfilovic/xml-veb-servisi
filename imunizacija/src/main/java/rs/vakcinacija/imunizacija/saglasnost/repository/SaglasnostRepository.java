package rs.vakcinacija.imunizacija.saglasnost.repository;

import org.springframework.stereotype.Component;
import rs.vakcinacija.imunizacija.repository.SpringExistRepository;
import rs.vakcinacija.imunizacija.saglasnost.model.SaglasnostZaSprovodjenjeImunizacije;

@Component
public class SaglasnostRepository extends SpringExistRepository<SaglasnostZaSprovodjenjeImunizacije> {

    public SaglasnostRepository() {
        super("saglasnost", SaglasnostZaSprovodjenjeImunizacije.class);
    }
}
