package rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model.PotvrdaOVakcinaciji;
import rs.vakcinacija.zajednicko.data.connection.ExistConnectionProvider;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;

@Component
public class PotvrdaOVakcinacijiRepository extends ExistRepository<PotvrdaOVakcinaciji> {

    @Autowired
    public PotvrdaOVakcinacijiRepository(ExistConnectionProvider connectionProvider) {
        super("potvrda-o-vakcinaciji", PotvrdaOVakcinaciji.class, connectionProvider);
    }
}
