package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model.IzvestajOImunizaciji;
import rs.vakcinacija.zajednicko.data.connection.ExistConnectionProvider;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;

@Component
public class IzvestajOImunizacijiExistRepository extends ExistRepository<IzvestajOImunizaciji> {

    @Autowired
    public IzvestajOImunizacijiExistRepository(ExistConnectionProvider connectionProvider) {
        super("izvestaj-o-imunizaciji", IzvestajOImunizaciji.class, connectionProvider);
    }
}
