package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model.IzvestajOImunizaciji;
import rs.vakcinacija.zajednicko.metadata.connection.FusekiConnectionProvider;
import rs.vakcinacija.zajednicko.metadata.repository.FusekiRepository;


@Component
public class IzvestajOImunizacijiFusekiRepository extends FusekiRepository<IzvestajOImunizaciji> {


    @Autowired
    public IzvestajOImunizacijiFusekiRepository(FusekiConnectionProvider connectionProvider) {
        super("izvestaj-o-imunizaciji", IzvestajOImunizaciji.class, connectionProvider);
    }
}
