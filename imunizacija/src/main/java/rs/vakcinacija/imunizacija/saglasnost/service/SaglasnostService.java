package rs.vakcinacija.imunizacija.saglasnost.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.imunizacija.saglasnost.model.SaglasnostZaSprovodjenjeImunizacije;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;
import rs.vakcinacija.zajednicko.metadata.repository.FusekiRepository;
import rs.vakcinacija.zajednicko.service.DocumentService;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Slf4j
public class SaglasnostService extends DocumentService<SaglasnostZaSprovodjenjeImunizacije> {

    @Autowired
    protected SaglasnostService(ExistRepository<SaglasnostZaSprovodjenjeImunizacije> existRepository,
                                FusekiRepository<SaglasnostZaSprovodjenjeImunizacije> fusekiRepository) {
        super(existRepository, fusekiRepository);
    }

    @Override
    public SaglasnostZaSprovodjenjeImunizacije create(SaglasnostZaSprovodjenjeImunizacije saglasnost) throws Exception {
        var id = this.existRepository.save(saglasnost);
        this.fusekiRepository.save(id, saglasnost);
        return saglasnost;
    }

    @Override
    public SaglasnostZaSprovodjenjeImunizacije read(UUID uuid) throws Exception {
        return this.existRepository.read(uuid)
                .orElseThrow(() -> new RuntimeException("Cannot find saglasnost with id: " + uuid.toString()));
    }

    public SaglasnostZaSprovodjenjeImunizacije insertRDFAttributes(SaglasnostZaSprovodjenjeImunizacije saglasnost) {
        var datum = saglasnost.getDatum();
        datum.setProperty("pred:datum_izdavanja");
        datum.setDatatype("xs:date");

        var pacijent = saglasnost.getPacijent();
        var punoImePacijenta = pacijent.getLicneInformacije().getPunoIme();
        var lekar = saglasnost.getVakcinacija().getLekar();
        pacijent.setVocab("https://www.vakcinacija.rs/rdf/predicate/");
        pacijent.setAbout("https://www.vakcinacija.rs/rdf/pacijent/" + punoImePacijenta.getIme().getValue() + "_" + punoImePacijenta.getPrezime().getValue());
        pacijent.setRel("pred:vaccinatedBy");
        pacijent.setTypeof("pred:Pacijent");
        pacijent.setHref("https://www.vakcinacija.rs/rdf/lekar/" + lekar.getIme().getValue() + "_" + lekar.getPrezime().getValue());
        return saglasnost;
    }
}
