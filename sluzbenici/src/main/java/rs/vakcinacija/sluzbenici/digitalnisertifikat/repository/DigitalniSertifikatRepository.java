package rs.vakcinacija.sluzbenici.digitalnisertifikat.repository;

import org.springframework.stereotype.Component;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.model.DigitalniSertifikat;
import rs.vakcinacija.sluzbenici.zajednicko.repository.ExistRepository;

@Component
public class DigitalniSertifikatRepository extends ExistRepository<DigitalniSertifikat> {
    public DigitalniSertifikatRepository() {
        super("digitalni-sertifikat", DigitalniSertifikat.class);
    }
}
