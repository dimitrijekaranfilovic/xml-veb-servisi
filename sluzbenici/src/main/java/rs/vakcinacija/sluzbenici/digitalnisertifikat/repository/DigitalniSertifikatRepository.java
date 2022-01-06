package rs.vakcinacija.sluzbenici.digitalnisertifikat.repository;

import org.springframework.stereotype.Component;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.model.DigitalniSertifikat;
import rs.vakcinacija.sluzbenici.repository.SpringExistRepository;


@Component
public class DigitalniSertifikatRepository extends SpringExistRepository<DigitalniSertifikat> {
    public DigitalniSertifikatRepository() {
        super("digitalni-sertifikat", DigitalniSertifikat.class);
    }
}
