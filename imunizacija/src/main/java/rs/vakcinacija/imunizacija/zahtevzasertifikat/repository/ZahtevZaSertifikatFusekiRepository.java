package rs.vakcinacija.imunizacija.zahtevzasertifikat.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.vakcinacija.imunizacija.zahtevzasertifikat.model.ZahtevZaSertifikat;
import rs.vakcinacija.zajednicko.metadata.connection.FusekiConnectionProvider;
import rs.vakcinacija.zajednicko.metadata.repository.FusekiRepository;

@Component
public class ZahtevZaSertifikatFusekiRepository extends FusekiRepository<ZahtevZaSertifikat> {
    @Autowired
    public ZahtevZaSertifikatFusekiRepository(FusekiConnectionProvider connectionProvider) {
        super("zahtev_za_sertifikat", ZahtevZaSertifikat.class, connectionProvider);
    }
}
