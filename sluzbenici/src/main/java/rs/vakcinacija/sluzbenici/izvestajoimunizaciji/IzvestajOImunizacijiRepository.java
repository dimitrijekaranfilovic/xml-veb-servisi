package rs.vakcinacija.sluzbenici.izvestajoimunizaciji;

import org.springframework.stereotype.Component;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model.IzvestajOImunizaciji;
import rs.vakcinacija.sluzbenici.zajednicko.repository.ExistRepository;

@Component
public class IzvestajOImunizacijiRepository extends ExistRepository<IzvestajOImunizaciji> {


    protected IzvestajOImunizacijiRepository() {
        super("izvestaj-o-imunizaciji", IzvestajOImunizaciji.class);
    }
}
