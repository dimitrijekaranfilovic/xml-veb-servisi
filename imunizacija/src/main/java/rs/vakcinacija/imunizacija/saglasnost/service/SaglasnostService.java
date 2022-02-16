package rs.vakcinacija.imunizacija.saglasnost.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.imunizacija.saglasnost.model.SaglasnostZaSprovodjenjeImunizacije;
import rs.vakcinacija.imunizacija.saglasnost.repository.SaglasnostExistRepository;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;
import rs.vakcinacija.zajednicko.metadata.repository.FusekiRepository;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.service.DocumentService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class SaglasnostService extends DocumentService<SaglasnostZaSprovodjenjeImunizacije> {

    private final SaglasnostExistRepository saglasnostExistRepository;

    @Autowired
    public SaglasnostService(ExistRepository<SaglasnostZaSprovodjenjeImunizacije> existRepository,
                             FusekiRepository<SaglasnostZaSprovodjenjeImunizacije> fusekiRepository,
                             SaglasnostExistRepository saglasnostExistRepository) {
        super(existRepository, fusekiRepository);
        this.saglasnostExistRepository = saglasnostExistRepository;
    }

    public SaglasnostZaSprovodjenjeImunizacije createFirstHalfOfDocument(SaglasnostZaSprovodjenjeImunizacije saglasnost) throws Exception {
        saglasnost.setDatum(new RDFDate(new Date(), "", "", null, null, null, null, null));
        insertRDFMetadataForFirstHalf(saglasnost);
        var id = existRepository.save(saglasnost);
        fusekiRepository.save(id, saglasnost);
        return saglasnost;
    }

    public List<SaglasnostZaSprovodjenjeImunizacije> getAllForUser(String email) throws Exception {
        return existRepository.read((doc) -> doc.getPacijent().getLicneInformacije().getKontakt().getEmail().getValue().equals(email));
    }

    public String getHTMLRepresentation(UUID id) throws Exception {
        SaglasnostZaSprovodjenjeImunizacije saglasnost = read(id);
        return generateHTML(saglasnost, "src/main/resources/xslt/saglasnost.xsl");
    }

    public ByteArrayInputStream getPDFRepresentation(UUID id) throws Exception {
        SaglasnostZaSprovodjenjeImunizacije saglasnost = read(id);
        return generatePDF(saglasnost, "src/main/resources/xsl-fo/saglasnost_fo.xsl");
    }

    protected void insertRDFMetadata(SaglasnostZaSprovodjenjeImunizacije saglasnost) {
        saglasnost.getDatum().rdf().property(PROP_DATUM_IZDAVANJA).datatype(T_DATE);

        var pacijent = saglasnost.getPacijent();
        var vakcinacija = saglasnost.getVakcinacija();
        var lekar = saglasnost.getVakcinacija().getLekar();
        var drzavljanstvo = pacijent.getLicneInformacije().getDrzavljanstvo();

        String pacijentURL = RDF_PACIJENT_BASE + pacijent.getLicneInformacije().getKontakt().getEmail().getValue();

        var autogeneratedId = UUID.randomUUID();
        saglasnost.setId(autogeneratedId);
        saglasnost.rdf().vocab(VOCAB).about("https://www.vakcinacija.rs/saglasnost/" + autogeneratedId)
                .rel("pred:podnesenOd").typeof("pred:Saglasnost").href(pacijentURL);

        if (drzavljanstvo.getSrpskiDrzavljanin() != null) {
            drzavljanstvo.getSrpskiDrzavljanin().getJmbg().rdf().property(PROP_JMBG).datatype(T_STRING);
        } else {
            drzavljanstvo.getStraniDrzavljanin().getNazivDrzavljanstva().rdf().property("pred:naziv_drzavljanstva").datatype(T_STRING);
            drzavljanstvo.getStraniDrzavljanin().getBrojPasosa().rdf().property("pred:broj_pasosa").datatype(T_STRING);
        }

        String lekarURL;

        if (lekar.getTelefon().getBrojFiksnog() != null) {
            lekarURL = RDF_LEKAR_BASE + lekar.getTelefon().getBrojFiksnog().getValue();
        } else {
            lekarURL = RDF_LEKAR_BASE + lekar.getTelefon().getBrojMobilnog().getValue();
        }

        if (drzavljanstvo.getStraniDrzavljanin() != null) {
            pacijent.getLicneInformacije().getDrzavljanstvo().getStraniDrzavljanin().getNazivDrzavljanstva().rdf().property("pred:naziv_drzavljanstva").datatype(T_STRING);
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
    }

    protected void insertRDFMetadataForFirstHalf(SaglasnostZaSprovodjenjeImunizacije saglasnost) {
        saglasnost.getDatum().rdf().property(PROP_DATUM_IZDAVANJA).datatype(T_DATE);

        var pacijent = saglasnost.getPacijent();
        var drzavljanstvo = pacijent.getLicneInformacije().getDrzavljanstvo();

        String pacijentURL = RDF_PACIJENT_BASE + pacijent.getLicneInformacije().getKontakt().getEmail().getValue();

        var autogeneratedId = UUID.randomUUID();
        saglasnost.setId(autogeneratedId);
        saglasnost.rdf().vocab(VOCAB).about("https://www.vakcinacija.rs/saglasnost/" + autogeneratedId)
                .rel("pred:podnesenOd").typeof("pred:Saglasnost").href(pacijentURL);

        if (drzavljanstvo.getSrpskiDrzavljanin() != null) {
            drzavljanstvo.getSrpskiDrzavljanin().getJmbg().rdf().property(PROP_JMBG).datatype(T_STRING);
        } else {
            drzavljanstvo.getStraniDrzavljanin().getNazivDrzavljanstva().rdf().property("pred:naziv_drzavljanstva").datatype(T_STRING);
            drzavljanstvo.getStraniDrzavljanin().getBrojPasosa().rdf().property("pred:broj_pasosa").datatype(T_STRING);
        }

        if (drzavljanstvo.getStraniDrzavljanin() != null) {
            pacijent.getLicneInformacije().getDrzavljanstvo().getStraniDrzavljanin().getNazivDrzavljanstva().rdf().property("pred:naziv_drzavljanstva").datatype(T_STRING);
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
    }
}
