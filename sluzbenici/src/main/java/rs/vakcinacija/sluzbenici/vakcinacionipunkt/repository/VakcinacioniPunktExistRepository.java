package rs.vakcinacija.sluzbenici.vakcinacionipunkt.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.model.VakcinacioniPunkt;
import rs.vakcinacija.zajednicko.data.connection.ExistConnectionProvider;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;

@Component
public class VakcinacioniPunktExistRepository extends ExistRepository<VakcinacioniPunkt> {

    @Autowired
    public VakcinacioniPunktExistRepository(ExistConnectionProvider connectionProvider) {
        super("vakcinacioni-punkt", VakcinacioniPunkt.class, connectionProvider);
    }
}
