package rs.vakcinacija.imunizacija.interesovanje.repository;

import org.springframework.stereotype.Component;
import rs.vakcinacija.imunizacija.interesovanje.model.Interesovanje;
import rs.vakcinacija.imunizacija.zajednicko.repository.ExistRepository;

@Component
public class InteresovanjeRepository extends ExistRepository<Interesovanje> {

    protected InteresovanjeRepository() {
        super("interesovanje", Interesovanje.class);
    }
}
