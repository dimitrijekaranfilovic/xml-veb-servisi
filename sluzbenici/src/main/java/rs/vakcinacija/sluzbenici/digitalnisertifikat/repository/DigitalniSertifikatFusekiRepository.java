package rs.vakcinacija.sluzbenici.digitalnisertifikat.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.model.DigitalniSertifikat;
import rs.vakcinacija.zajednicko.metadata.connection.FusekiConnectionProvider;
import rs.vakcinacija.zajednicko.metadata.repository.FusekiRepository;

@Component
public class DigitalniSertifikatFusekiRepository extends FusekiRepository<DigitalniSertifikat> {
    @Autowired
    public DigitalniSertifikatFusekiRepository(FusekiConnectionProvider connectionProvider) {
        super("digitalni-sertifikat", DigitalniSertifikat.class, connectionProvider);
    }
}
