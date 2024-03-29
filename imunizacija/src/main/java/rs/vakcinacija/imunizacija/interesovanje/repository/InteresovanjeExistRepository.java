package rs.vakcinacija.imunizacija.interesovanje.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.vakcinacija.imunizacija.interesovanje.model.Interesovanje;
import rs.vakcinacija.zajednicko.data.connection.ExistConnectionProvider;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;

@Component
public class InteresovanjeExistRepository extends ExistRepository<Interesovanje> {

    @Autowired
    public InteresovanjeExistRepository(ExistConnectionProvider connectionProvider) {
        super("interesovanje", Interesovanje.class, connectionProvider);
    }
}
