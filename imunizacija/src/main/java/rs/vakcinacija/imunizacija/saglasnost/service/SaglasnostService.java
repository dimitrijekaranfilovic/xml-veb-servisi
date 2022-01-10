package rs.vakcinacija.imunizacija.saglasnost.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.imunizacija.saglasnost.model.SaglasnostZaSprovodjenjeImunizacije;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;
import rs.vakcinacija.zajednicko.metadata.repository.FusekiRepository;
import rs.vakcinacija.zajednicko.service.DocumentService;

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
        datum.setProperty(PROP_DATUM_IZDAVANJA);
        datum.setDatatype(T_DATE);

        var pacijent = saglasnost.getPacijent();
        var vakcinacija = saglasnost.getVakcinacija();
        var lekar = saglasnost.getVakcinacija().getLekar();
        var drzavljanstvo = pacijent.getLicneInformacije().getDrzavljanstvo();

        String pacijentURL;

        if (drzavljanstvo.getSrpskiDrzavljanin() != null) {
            drzavljanstvo.getSrpskiDrzavljanin().getJmbg().setProperty(PROP_JMBG);
            drzavljanstvo.getSrpskiDrzavljanin().getJmbg().setDatatype(T_STRING);
            pacijentURL = RDF_PACIJENT_BASE + drzavljanstvo.getSrpskiDrzavljanin().getJmbg().getValue();
        } else {
            drzavljanstvo.getStraniDrzavljanin().getNazivDrzave().setProperty("pred:naziv_drzave");
            drzavljanstvo.getStraniDrzavljanin().getNazivDrzave().setDatatype(T_STRING);
            drzavljanstvo.getStraniDrzavljanin().getBrojPasosa().setProperty("pred:broj_pasosa");
            drzavljanstvo.getStraniDrzavljanin().getBrojPasosa().setDatatype(T_STRING);
            pacijentURL = RDF_PACIJENT_BASE + drzavljanstvo.getStraniDrzavljanin().getNazivDrzave().getValue() + "_" + drzavljanstvo.getStraniDrzavljanin().getBrojPasosa().getValue();
        }

        lekar.setId(UUID.randomUUID());
        var lekarURL = RDF_LEKAR_BASE + lekar.getId().toString();

        pacijent.setVocab(VOCAB);
        pacijent.setAbout(pacijentURL);
        pacijent.setRel("pred:vaccinatedBy");
        pacijent.setTypeof("pred:Pacijent");
        pacijent.setHref(lekarURL);

        var punoImePacijenta = pacijent.getLicneInformacije().getPunoIme();
        punoImePacijenta.getIme().setProperty(PROP_IME);
        punoImePacijenta.getIme().setDatatype(T_STRING);
        punoImePacijenta.getPrezime().setProperty(PROP_PREZIME);
        punoImePacijenta.getPrezime().setDatatype(T_STRING);
        punoImePacijenta.getImeRoditelja().setProperty(PROP_IME_RODITELJA);
        punoImePacijenta.getImeRoditelja().setDatatype(T_STRING);

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

        if (lekar.getTelefon().getBrojFiksnog() != null) {
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
