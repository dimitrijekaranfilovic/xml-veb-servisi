package rs.vakcinacija.sluzbenici.vakcinacionipunkt.repository;

import org.springframework.stereotype.Component;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.model.VakcinacioniPunkt;
import rs.vakcinacija.zajednicko.metadata.connection.FusekiConnectionProvider;
import rs.vakcinacija.zajednicko.metadata.repository.FusekiRepository;

@Component
public class VakcinacioniPunktFusekiRepository extends FusekiRepository<VakcinacioniPunkt> {


    public VakcinacioniPunktFusekiRepository(FusekiConnectionProvider connectionProvider) {
        super("vakcinacioni-punkt", VakcinacioniPunkt.class, connectionProvider);
    }
}
