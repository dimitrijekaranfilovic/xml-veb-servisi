package rs.vakcinacija.sluzbenici.digitalnisertifikat.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.model.DigitalniSertifikat;
import rs.vakcinacija.zajednicko.connection.ConnectionProvider;
import rs.vakcinacija.zajednicko.repository.ExistRepository;


@Component
public class DigitalniSertifikatRepository extends ExistRepository<DigitalniSertifikat> {
    @Autowired
    public DigitalniSertifikatRepository(ConnectionProvider connectionProvider) {
        super("digitalni-sertifikat", DigitalniSertifikat.class, connectionProvider);
    }
}
