package rs.vakcinacija.imunizacija.interesovanje.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.vakcinacija.imunizacija.interesovanje.model.Interesovanje;
import rs.vakcinacija.zajednicko.connection.ConnectionProvider;
import rs.vakcinacija.zajednicko.repository.ExistRepository;

@Component
public class InteresovanjeRepository extends ExistRepository<Interesovanje> {

    @Autowired
    public InteresovanjeRepository(ConnectionProvider connectionProvider) {
        super("interesovanje", Interesovanje.class, connectionProvider);
    }
}
