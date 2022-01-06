package rs.vakcinacija.imunizacija.interesovanje.repository;

import org.springframework.stereotype.Component;
import rs.vakcinacija.imunizacija.interesovanje.model.Interesovanje;
import rs.vakcinacija.imunizacija.repository.SpringExistRepository;

@Component
public class InteresovanjeRepository extends SpringExistRepository<Interesovanje> {

    protected InteresovanjeRepository() {
        super("interesovanje", Interesovanje.class);
    }
}
