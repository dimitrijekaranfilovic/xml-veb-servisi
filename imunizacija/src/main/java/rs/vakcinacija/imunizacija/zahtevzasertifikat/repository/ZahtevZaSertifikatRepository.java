package rs.vakcinacija.imunizacija.zahtevzasertifikat.repository;

import org.springframework.stereotype.Component;
import rs.vakcinacija.imunizacija.zahtevzasertifikat.model.ZahtevZaSertifikat;
import rs.vakcinacija.imunizacija.zajednicko.repository.ExistRepository;

@Component
public class ZahtevZaSertifikatRepository extends ExistRepository<ZahtevZaSertifikat> {
    protected ZahtevZaSertifikatRepository() {
        super("zahtev_za_sertifikat", ZahtevZaSertifikat.class);
    }
}
