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
        saglasnost.getDatum().rdf().property(PROP_DATUM_IZDAVANJA).datatype(T_DATE);

        var pacijent = saglasnost.getPacijent();
        var vakcinacija = saglasnost.getVakcinacija();
        var lekar = saglasnost.getVakcinacija().getLekar();
        var drzavljanstvo = pacijent.getLicneInformacije().getDrzavljanstvo();

        String pacijentURL;

        if (drzavljanstvo.getSrpskiDrzavljanin() != null) {
            drzavljanstvo.getSrpskiDrzavljanin().getJmbg().rdf().property(PROP_JMBG).datatype(T_STRING);
            pacijentURL = RDF_PACIJENT_BASE + drzavljanstvo.getSrpskiDrzavljanin().getJmbg().getValue();
        } else {
            drzavljanstvo.getStraniDrzavljanin().getNazivDrzave().rdf().property("pred:naziv_drzave").datatype(T_STRING);
            drzavljanstvo.getStraniDrzavljanin().getBrojPasosa().rdf().property("pred:broj_pasosa").datatype(T_STRING);
            pacijentURL = RDF_PACIJENT_BASE + drzavljanstvo.getStraniDrzavljanin().getBrojPasosa().getValue();
        }

        String lekarURL;

        if (lekar.getTelefon().getBrojFiksnog() != null) {
            lekarURL = RDF_LEKAR_BASE + lekar.getTelefon().getBrojFiksnog().getValue();
        } else {
            lekarURL = RDF_LEKAR_BASE + lekar.getTelefon().getBrojMobilnog().getValue();
        }

        pacijent.rdf().vocab(VOCAB).about(pacijentURL).rel("pred:vakcinisan_od").typeof("pred:Pacijent").href(lekarURL);
        if (drzavljanstvo.getStraniDrzavljanin() != null) {
            pacijent.getLicneInformacije().getDrzavljanstvo().getStraniDrzavljanin().getNazivDrzave().rdf().property("pred:naziv_drzave").datatype(T_STRING);
        }

        var punoImePacijenta = pacijent.getLicneInformacije().getPunoIme();
        punoImePacijenta.getIme().rdf().property(PROP_IME).datatype(T_STRING);
        punoImePacijenta.getPrezime().rdf().property(PROP_PREZIME).datatype(T_STRING);
        punoImePacijenta.getImeRoditelja().rdf().property(PROP_IME_RODITELJA).datatype(T_STRING);

        var kontakt = pacijent.getLicneInformacije().getKontakt();
        kontakt.getBrojFiksnog().rdf().property(PROP_BROJ_FIKSNOG).datatype(T_STRING);
        kontakt.getBrojMobilnog().rdf().property(PROP_BROJ_MOBILNOG).datatype(T_STRING);
        kontakt.getEmail().rdf().property(PROP_EMAIL).datatype(T_STRING);

        pacijent.getLicneInformacije().getRadniStatus().rdf().property("pred:radni_status").datatype(T_STRING);
        pacijent.getLicneInformacije().getZanimanjeZaposlenog().rdf().property("pred:zanimanje").datatype(T_STRING);
        pacijent.getSaglasnost().getNazivImunoloskogLeka().rdf().property("pred:cip").datatype(T_STRING);

        var zdravstvenaUstanova = vakcinacija.getZdravstvenaUstanova();
        zdravstvenaUstanova.getNaziv().rdf().property("pred:ustanova").datatype(T_STRING);
        zdravstvenaUstanova.getPunkt().rdf().property("pred:punkt").datatype(T_STRING);

        lekar.rdf().vocab(VOCAB).about(lekarURL);
        lekar.getIme().rdf().property(PROP_IME).datatype(T_STRING);
        lekar.getPrezime().rdf().property(PROP_PREZIME).datatype(T_STRING);

        vakcinacija.getOdlukaKomisije().rdf().property("pred:trajne_kontraindikacije").datatype(T_BOOLEAN);

        return saglasnost;
    }
}
