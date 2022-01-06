package rs.vakcinacija.imunizacija.zahtevzasertifikat.repository;

import org.springframework.stereotype.Component;
import rs.vakcinacija.imunizacija.repository.SpringExistRepository;
import rs.vakcinacija.imunizacija.zahtevzasertifikat.model.ZahtevZaSertifikat;

@Component
public class ZahtevZaSertifikatRepository extends SpringExistRepository<ZahtevZaSertifikat> {
    protected ZahtevZaSertifikatRepository() {
        super("zahtev_za_sertifikat", ZahtevZaSertifikat.class);
    }
}
