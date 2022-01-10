package rs.vakcinacija.imunizacija.saglasnost.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.vakcinacija.imunizacija.saglasnost.model.SaglasnostZaSprovodjenjeImunizacije;
import rs.vakcinacija.zajednicko.data.connection.ExistConnectionProvider;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;

@Component
public class SaglasnostExistRepository extends ExistRepository<SaglasnostZaSprovodjenjeImunizacije> {

    @Autowired
    public SaglasnostExistRepository(ExistConnectionProvider connectionProvider) {
        super("saglasnost", SaglasnostZaSprovodjenjeImunizacije.class, connectionProvider);
    }
}
