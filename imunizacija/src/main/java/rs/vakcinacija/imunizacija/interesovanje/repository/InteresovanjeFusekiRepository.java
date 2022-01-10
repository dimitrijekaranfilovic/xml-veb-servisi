package rs.vakcinacija.imunizacija.interesovanje.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.vakcinacija.imunizacija.interesovanje.model.Interesovanje;
import rs.vakcinacija.zajednicko.metadata.connection.FusekiConnectionProvider;
import rs.vakcinacija.zajednicko.metadata.repository.FusekiRepository;

@Component
public class InteresovanjeFusekiRepository extends FusekiRepository<Interesovanje> {

    @Autowired
    public InteresovanjeFusekiRepository(FusekiConnectionProvider connectionProvider) {
        super("interesovanje", Interesovanje.class, connectionProvider);
    }
}
