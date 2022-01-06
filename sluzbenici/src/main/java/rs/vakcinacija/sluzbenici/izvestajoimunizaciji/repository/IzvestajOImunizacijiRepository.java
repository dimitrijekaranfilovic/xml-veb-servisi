package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.repository;

import org.springframework.stereotype.Component;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model.IzvestajOImunizaciji;
import rs.vakcinacija.sluzbenici.repository.SpringExistRepository;

@Component
public class IzvestajOImunizacijiRepository extends SpringExistRepository<IzvestajOImunizaciji> {


    protected IzvestajOImunizacijiRepository() {
        super("izvestaj-o-imunizaciji", IzvestajOImunizaciji.class);
    }
}
