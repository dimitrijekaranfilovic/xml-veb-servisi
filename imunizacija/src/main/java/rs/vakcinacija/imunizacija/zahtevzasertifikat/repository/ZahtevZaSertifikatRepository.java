package rs.vakcinacija.imunizacija.zahtevzasertifikat.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.vakcinacija.imunizacija.zahtevzasertifikat.model.ZahtevZaSertifikat;
import rs.vakcinacija.zajednicko.data.connection.ExistConnectionProvider;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;

@Component
public class ZahtevZaSertifikatRepository extends ExistRepository<ZahtevZaSertifikat> {

    @Autowired
    public ZahtevZaSertifikatRepository(ExistConnectionProvider connectionProvider) {
        super("zahtev_za_sertifikat", ZahtevZaSertifikat.class, connectionProvider);
    }
}
