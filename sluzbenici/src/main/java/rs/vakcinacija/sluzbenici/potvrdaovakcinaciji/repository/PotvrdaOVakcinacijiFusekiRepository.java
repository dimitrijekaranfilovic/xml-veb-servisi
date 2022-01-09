package rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model.PotvrdaOVakcinaciji;
import rs.vakcinacija.zajednicko.metadata.connection.FusekiConnectionProvider;
import rs.vakcinacija.zajednicko.metadata.repository.FusekiRepository;

@Component
public class PotvrdaOVakcinacijiFusekiRepository extends FusekiRepository<PotvrdaOVakcinaciji> {

    @Autowired
    public PotvrdaOVakcinacijiFusekiRepository(FusekiConnectionProvider connectionProvider) {
        super("potvrda-o-vakcinaciji", PotvrdaOVakcinaciji.class, connectionProvider);
    }
}
