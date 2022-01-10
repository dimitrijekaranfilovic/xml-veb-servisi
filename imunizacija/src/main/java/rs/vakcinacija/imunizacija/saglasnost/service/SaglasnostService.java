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

    public static final String VOCAB = "https://www.vakcinacija.rs/rdf/predicate/";
    public static final String RDF_LEKAR_BASE = "https://www.vakcinacija.rs/rdf/lekar/";
    public static final String RDF_PACIJENT_BASE = "https://www.vakcinacija.rs/rdf/pacijent/";

    public static final String PROP_DATUM_IZDAVANJA = "pred:datum_izdavanja";
    public static final String PROP_JMBG = "pred:jmbg";
    public static final String PROP_BROJ_FIKSNOG = "pred:broj_fiksnog";
    public static final String PROP_BROJ_MOBILNOG = "pred:broj_mobilnog";
    public static final String PROP_EMAIL = "pred:email";

    public static final String T_STRING = "xs:string";
    public static final String T_DATE = "xs:date";

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
        datum.setProperty(PROP_DATUM_IZDAVANJA);
        datum.setDatatype(T_DATE);

        var pacijent = saglasnost.getPacijent();
        var vakcinacija = saglasnost.getVakcinacija();
        var punoImePacijenta = pacijent.getLicneInformacije().getPunoIme();
        var lekar = saglasnost.getVakcinacija().getLekar();
        var pacijentURL = RDF_PACIJENT_BASE + punoImePacijenta.getIme().getValue() + "_" + punoImePacijenta.getPrezime().getValue();
        var lekarURL = RDF_LEKAR_BASE + lekar.getIme().getValue() + "_" + lekar.getPrezime().getValue();

        pacijent.setVocab(VOCAB);
        pacijent.setAbout(pacijentURL);
        pacijent.setRel("pred:vaccinatedBy");
        pacijent.setTypeof("pred:Pacijent");
        pacijent.setHref(lekarURL);

        var drzavljanstvo = pacijent.getLicneInformacije().getDrzavljanstvo();

        if(drzavljanstvo.getSrpskiDrzavljanin() != null) {
            drzavljanstvo.getSrpskiDrzavljanin().getJmbg().setProperty(PROP_JMBG);
            drzavljanstvo.getSrpskiDrzavljanin().getJmbg().setDatatype(T_STRING);
        } else {
            drzavljanstvo.getStraniDrzavljanin().getNazivDrzave().setProperty("pred:naziv_drzave");
            drzavljanstvo.getStraniDrzavljanin().getNazivDrzave().setDatatype(T_STRING);
            drzavljanstvo.getStraniDrzavljanin().getBrojPasosa().setProperty("pred:broj_pasosa");
            drzavljanstvo.getStraniDrzavljanin().getBrojPasosa().setDatatype(T_STRING);
        }

        var kontakt = pacijent.getLicneInformacije().getKontakt();
        kontakt.getBrojFiksnog().setProperty(PROP_BROJ_FIKSNOG);
        kontakt.getBrojFiksnog().setDatatype(T_STRING);
        kontakt.getBrojMobilnog().setProperty(PROP_BROJ_MOBILNOG);
        kontakt.getBrojMobilnog().setDatatype(T_STRING);
        kontakt.getEmail().setProperty(PROP_EMAIL);
        kontakt.getEmail().setDatatype(T_STRING);

        var radniStatus = pacijent.getLicneInformacije().getRadniStatus();
        radniStatus.setProperty("pred:radni_status");
        radniStatus.setDatatype(T_STRING);

        var zanimanje = pacijent.getLicneInformacije().getZanimanjeZaposlenog();
        zanimanje.setProperty("pred:zanimanje");
        zanimanje.setDatatype(T_STRING);

        var imunoloskiLek = pacijent.getSaglasnost().getNazivImunoloskogLeka();
        imunoloskiLek.setProperty("pred:cip");
        imunoloskiLek.setDatatype(T_STRING);

        var zdravstvenaUstanova = vakcinacija.getZdravstvenaUstanova();
        zdravstvenaUstanova.getNaziv().setProperty("pred:ustanova");
        zdravstvenaUstanova.getNaziv().setDatatype(T_STRING);
        zdravstvenaUstanova.getPunkt().setProperty("pred:punkt");
        zdravstvenaUstanova.getPunkt().setDatatype(T_STRING);

        lekar.setVocab(VOCAB);
        lekar.setAbout(lekarURL);

        if(lekar.getTelefon().getBrojFiksnog() != null) {
            lekar.getTelefon().getBrojFiksnog().setProperty(PROP_BROJ_FIKSNOG);
            lekar.getTelefon().getBrojFiksnog().setDatatype(T_STRING);
        } else {
            lekar.getTelefon().getBrojMobilnog().setProperty(PROP_BROJ_MOBILNOG);
            lekar.getTelefon().getBrojMobilnog().setDatatype(T_STRING);
        }

        vakcinacija.getOdlukaKomisije().setProperty("pred:trajne_kontraindikacije");
        vakcinacija.getOdlukaKomisije().setDatatype(T_STRING);

        return saglasnost;
    }
}
