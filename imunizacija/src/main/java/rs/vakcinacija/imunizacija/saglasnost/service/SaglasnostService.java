package rs.vakcinacija.imunizacija.saglasnost.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.jena.dboe.migrate.L;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import rs.vakcinacija.imunizacija.saglasnost.dto.KontraindikacijaDTO;
import rs.vakcinacija.imunizacija.saglasnost.dto.LekarDTO;
import rs.vakcinacija.imunizacija.saglasnost.dto.ZdravstvenaUstanovaDTO;
import rs.vakcinacija.imunizacija.saglasnost.model.*;
import rs.vakcinacija.imunizacija.saglasnost.repository.SaglasnostExistRepository;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;
import rs.vakcinacija.zajednicko.metadata.repository.FusekiRepository;
import rs.vakcinacija.zajednicko.model.RDFBoolean;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.model.RDFInteger;
import rs.vakcinacija.zajednicko.model.RDFString;
import rs.vakcinacija.zajednicko.service.DocumentService;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public List<SaglasnostZaSprovodjenjeImunizacije> readFiltered(String email) throws Exception {
        var lista = this.read();
        return lista.stream().filter(saglasnost ->
            saglasnost.getPacijent().getLicneInformacije().getKontakt().getEmail().getValue().contains(email)
        ).collect(Collectors.toList());
    }

    public void changeCommissionDecision(UUID id) throws Exception {
        var saglasnost = this.read(id);
        var odlukaKomisije = saglasnost.getVakcinacija().getOdlukaKomisije().getValue();
        saglasnost.getVakcinacija().setOdlukaKomisije(RDFBoolean.of(!odlukaKomisije));
        this.saglasnostExistRepository.save(saglasnost);
    }

    public void addSideEffect(UUID id, KontraindikacijaDTO dto) throws Exception {
        var saglasnost = this.read(id);
        var kontraindikacije = saglasnost.getVakcinacija().getPrivremeneKontraindikacije();
        var listaKontraindikacija = kontraindikacije.getKontraindikacije();
        if (listaKontraindikacija == null)
            kontraindikacije.setKontraindikacije(new ArrayList<>());
        kontraindikacije.getKontraindikacije().add(new Kontraindikacija(RDFDate.of(dto.getDatumUtvrdjivanja()), RDFString.of(dto.getDijagnoza())));
        this.saglasnostExistRepository.save(saglasnost);
    }


    public SaglasnostZaSprovodjenjeImunizacije createDoctorBuilding(UUID id, LekarDTO lekarDTO, ZdravstvenaUstanovaDTO zdravstvenaUstanovaDTO) throws Exception {
        var saglasnost = this.read(id);
        var vakcinacija = saglasnost.getVakcinacija();
        var lekar = vakcinacija.getLekar();
        var telefon = lekar.getTelefon();
        var telefonDTO = lekarDTO.getTelefonDTO();
        var ustanova = vakcinacija.getZdravstvenaUstanova();

        telefon.setBrojFiksnog(RDFString.of(telefonDTO.getBrojFiksnog()));
        telefon.setBrojMobilnog(RDFString.of(telefonDTO.getBrojMobilnog()));

        lekar.setIme(RDFString.of(lekarDTO.getIme()));
        lekar.setPrezime(RDFString.of(lekarDTO.getPrezime()));

        ustanova.setNaziv(RDFString.of(zdravstvenaUstanovaDTO.getNaziv()));
        ustanova.setPunkt(RDFInteger.of(zdravstvenaUstanovaDTO.getPunkt()));

        this.existRepository.save(saglasnost);
        return saglasnost;
    }

    public SaglasnostZaSprovodjenjeImunizacije createFirstHalfOfDocument(SaglasnostZaSprovodjenjeImunizacije saglasnost) throws Exception {
        saglasnost.setDatum(new RDFDate(new Date(), "", "", null, null, null, null, null));

        saglasnost.setVakcinacija(new Vakcinacija());
        saglasnost.getVakcinacija().setZdravstvenaUstanova(new ZdravstvenaUstanova());
        saglasnost.getVakcinacija().setLekar(new Lekar());
        saglasnost.getVakcinacija().getLekar().setTelefon(new Telefon());
        saglasnost.getVakcinacija().setVakcine(new Vakcine());
        saglasnost.getVakcinacija().setOdlukaKomisije(RDFBoolean.of(false));
        saglasnost.getVakcinacija().setPrivremeneKontraindikacije(new PrivremeneKontraindikacije());
        saglasnost.getVakcinacija().getPrivremeneKontraindikacije().setKontraindikacije(new ArrayList<>());
        saglasnost.getVakcinacija().getVakcine().setVakcine(new ArrayList<>());

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

        pacijent.rdf().vocab(VOCAB).about(pacijentURL).rel("pred:vakcinisan_od").typeof("pred:Pacijent").href(lekarURL);
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

        pacijent.rdf().vocab(VOCAB).about(pacijentURL);

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
