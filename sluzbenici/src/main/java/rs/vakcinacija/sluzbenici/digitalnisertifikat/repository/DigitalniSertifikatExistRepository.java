package rs.vakcinacija.sluzbenici.digitalnisertifikat.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.model.DigitalniSertifikat;
import rs.vakcinacija.zajednicko.data.connection.ExistConnectionProvider;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;


@Component
public class DigitalniSertifikatExistRepository extends ExistRepository<DigitalniSertifikat> {
    @Autowired
    public DigitalniSertifikatExistRepository(ExistConnectionProvider connectionProvider) {
        super("digitalni-sertifikat", DigitalniSertifikat.class, connectionProvider);
    }
}
