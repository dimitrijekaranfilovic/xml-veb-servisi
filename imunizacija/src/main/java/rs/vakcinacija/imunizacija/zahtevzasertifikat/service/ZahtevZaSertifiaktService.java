package rs.vakcinacija.imunizacija.zahtevzasertifikat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.imunizacija.zahtevzasertifikat.model.ZahtevZaSertifikat;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;
import rs.vakcinacija.zajednicko.metadata.repository.FusekiRepository;
import rs.vakcinacija.zajednicko.service.DocumentService;

import java.util.UUID;

@Service
public class ZahtevZaSertifiaktService extends DocumentService<ZahtevZaSertifikat> {

    @Autowired
    public ZahtevZaSertifiaktService(ExistRepository<ZahtevZaSertifikat> existRepository,
                                     FusekiRepository<ZahtevZaSertifikat> fusekiRepository) {
        super(existRepository, fusekiRepository);
    }

    @Override
    public ZahtevZaSertifikat create(ZahtevZaSertifikat zahtevZaSertifikat) throws Exception {
        var id = existRepository.save(zahtevZaSertifikat);
        fusekiRepository.save(id, zahtevZaSertifikat);
        return zahtevZaSertifikat;
    }

    @Override
    public ZahtevZaSertifikat read(UUID id) throws Exception {
        return existRepository.read(id)
                .orElseThrow(() -> new RuntimeException("Cannot find zahtev za sertifikat with id: " + id));
    }
}
