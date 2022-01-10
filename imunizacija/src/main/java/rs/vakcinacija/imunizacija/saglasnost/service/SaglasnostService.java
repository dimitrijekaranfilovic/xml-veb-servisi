package rs.vakcinacija.imunizacija.saglasnost.service;

import org.springframework.stereotype.Service;
import rs.vakcinacija.imunizacija.saglasnost.model.SaglasnostZaSprovodjenjeImunizacije;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;
import rs.vakcinacija.zajednicko.metadata.repository.FusekiRepository;
import rs.vakcinacija.zajednicko.service.DocumentService;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class SaglasnostService extends DocumentService<SaglasnostZaSprovodjenjeImunizacije> {

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
                .orElseThrow(() -> new NoSuchElementException("Cannot find saglasnost with id: " + uuid.toString()));
    }

    public SaglasnostZaSprovodjenjeImunizacije insertRDFAttributes(SaglasnostZaSprovodjenjeImunizacije saglasnost) {
        var datum = saglasnost.getDatum();
        Date d = datum.getValue();
        datum.setValue(new Date());
        datum.setProperty("pred:datum_izdavanja");
        datum.setDatatype("xs:date");

//        var pacijent = saglasnost.getPacijent().getValue();
//        var punoImePacijenta = pacijent.getValue().getLicneInformacije().getValue().getPunoIme();
//        var lekar = saglasnost.getVakcinacija().getValue().getLekar();
//        pacijent.setVocab("https://www.vakcinacija.rs/rdf/predicate/");
//        pacijent.setAbout("https://www.vakcinacija.rs/rdf/pacijent/" + punoImePacijenta.getValue().getIme().getValue() + "_" + punoImePacijenta.getValue().getPrezime().getValue());
//        pacijent.setRel("pred:vaccinatedBy");
//        pacijent.setTypeof("pred:Pacijent");
//        pacijent.setHref("https://www.vakcinacija.rs/rdf/lekar/" + lekar.getValue().getIme().getValue() + "_" + lekar.getValue().getPrezime().getValue());
        return saglasnost;
    }
}
