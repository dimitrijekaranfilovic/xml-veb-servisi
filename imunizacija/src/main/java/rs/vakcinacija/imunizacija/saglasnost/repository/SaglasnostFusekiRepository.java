package rs.vakcinacija.imunizacija.saglasnost.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.vakcinacija.imunizacija.saglasnost.model.SaglasnostZaSprovodjenjeImunizacije;
import rs.vakcinacija.zajednicko.metadata.connection.FusekiConnectionProvider;
import rs.vakcinacija.zajednicko.metadata.repository.FusekiRepository;

@Component
public class SaglasnostFusekiRepository extends FusekiRepository<SaglasnostZaSprovodjenjeImunizacije> {
    @Autowired
    public SaglasnostFusekiRepository(FusekiConnectionProvider connectionProvider) {
        super("obrazac_saglasnosti", SaglasnostZaSprovodjenjeImunizacije.class, connectionProvider);
    }
}
