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

    public ZahtevZaSertifikat insertRDFAttributes(ZahtevZaSertifikat zahtevZaSertifikat) {
        var datum = zahtevZaSertifikat.getDatum();
        datum.setProperty(PROP_DATUM);
        datum.setDatatype(T_DATE);

        var podnosilacZahteva = zahtevZaSertifikat.getPodnosilacZahteva();

        String podnosilacUrl;
        if (podnosilacZahteva.getLicniPodaci().getJmbg() != null) {
            podnosilacUrl = RDF_PACIJENT_BASE + podnosilacZahteva.getLicniPodaci().getJmbg();
            var jmbg = zahtevZaSertifikat.getPodnosilacZahteva().getLicniPodaci().getJmbg();
            jmbg.setProperty(PROP_JMBG);
            jmbg.setDatatype(T_STRING);
        } else {
            podnosilacUrl = RDF_PACIJENT_BASE + podnosilacZahteva.getBrojPasosa();
        }

        podnosilacZahteva.setVocab(VOCAB);
        podnosilacZahteva.setAbout(podnosilacUrl);

        var brojPasosa = zahtevZaSertifikat.getPodnosilacZahteva().getBrojPasosa();
        brojPasosa.setProperty(PROP_BROJ_PASOSA);
        brojPasosa.setDatatype(T_STRING);

        var mesto = zahtevZaSertifikat.getMesto();
        mesto.setProperty(PROP_MESTO);
        mesto.setDatatype(T_STRING);

        return zahtevZaSertifikat;
    }

}