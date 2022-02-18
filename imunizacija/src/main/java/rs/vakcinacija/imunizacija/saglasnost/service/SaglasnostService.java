package rs.vakcinacija.imunizacija.saglasnost.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.imunizacija.interesovanje.service.InteresovanjeService;
import rs.vakcinacija.imunizacija.saglasnost.dto.KontraindikacijaDTO;
import rs.vakcinacija.imunizacija.saglasnost.dto.LekarDTO;
import rs.vakcinacija.imunizacija.saglasnost.dto.VakcinaDTO;
import rs.vakcinacija.imunizacija.saglasnost.dto.ZdravstvenaUstanovaDTO;
import rs.vakcinacija.imunizacija.saglasnost.exception.DoseException;
import rs.vakcinacija.imunizacija.saglasnost.exception.InteresovanjeNotSubmittedException;
import rs.vakcinacija.imunizacija.saglasnost.model.*;
import rs.vakcinacija.imunizacija.saglasnost.repository.SaglasnostExistRepository;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;
import rs.vakcinacija.zajednicko.metadata.repository.FusekiRepository;
import rs.vakcinacija.zajednicko.model.RDFBoolean;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.model.RDFInteger;
import rs.vakcinacija.zajednicko.model.RDFString;
import rs.vakcinacija.zajednicko.service.DocumentService;

import java.io.ByteArrayInputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SaglasnostService extends DocumentService<SaglasnostZaSprovodjenjeImunizacije> {

    private final SaglasnostExistRepository saglasnostExistRepository;
    private final InteresovanjeService interesovanjeService;

    @Autowired
    public SaglasnostService(ExistRepository<SaglasnostZaSprovodjenjeImunizacije> existRepository,
                             FusekiRepository<SaglasnostZaSprovodjenjeImunizacije> fusekiRepository,
                             SaglasnostExistRepository saglasnostExistRepository,
                             InteresovanjeService interesovanjeService) {
        super(existRepository, fusekiRepository);
        this.saglasnostExistRepository = saglasnostExistRepository;
        this.interesovanjeService = interesovanjeService;
    }

    public List<SaglasnostZaSprovodjenjeImunizacije> readFiltered(String email) throws Exception {
        var lista = this.read();
        return lista.stream().filter(saglasnost ->
            saglasnost.getPacijent().getLicneInformacije().getKontakt().getEmail().getValue().contains(email)
        ).collect(Collectors.toList());
    }

    public void changeCommissionDecision(UUID id) throws Exception {
        var saglasnost = this.read(id);
        var vakcineList = saglasnost.getVakcinacija().getVakcine().getVakcine();
        if(vakcineList == null){
            throw new DoseException(String.format("Одлука не може бити промењена за сагласност %s јер ниједна доза није додата.", saglasnost.getId()));
        }
        var odlukaKomisije = saglasnost.getVakcinacija().getOdlukaKomisije().getValue();
        saglasnost.getVakcinacija().setOdlukaKomisije(RDFBoolean.of(!odlukaKomisije));
        this.saglasnostExistRepository.save(saglasnost);
    }

    public void addSideEffect(UUID id, KontraindikacijaDTO dto) throws Exception {
        var saglasnost = this.read(id);
        var vakcineList = saglasnost.getVakcinacija().getVakcine().getVakcine();
        if(vakcineList == null){
            throw new DoseException(String.format("Нуспојава не може бити додата за сагласност %s јер ниједна доза није додата.", saglasnost.getId()));
        }
        var kontraindikacije = saglasnost.getVakcinacija().getPrivremeneKontraindikacije();
        var listaKontraindikacija = kontraindikacije.getKontraindikacije();
        if (listaKontraindikacija == null)
            kontraindikacije.setKontraindikacije(new ArrayList<>());
        kontraindikacije.getKontraindikacije().add(new Kontraindikacija(RDFDate.of(dto.getDatumUtvrdjivanja()), RDFString.of(dto.getDijagnoza())));
        this.saglasnostExistRepository.save(saglasnost);
    }


    public SaglasnostZaSprovodjenjeImunizacije createDoctorBuilding(UUID id, LekarDTO lekarDTO, ZdravstvenaUstanovaDTO zdravstvenaUstanovaDTO) throws Exception {
        //TODO: mozda baci exception ako je vec dodato
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

    public SaglasnostZaSprovodjenjeImunizacije addVaccine(UUID id, VakcinaDTO vakcinaDTO) throws Exception {
        var saglasnost = this.read(id);
        var vakcine = saglasnost.getVakcinacija().getVakcine();
        if (vakcine.getVakcine() == null)
            vakcine.setVakcine(new ArrayList<>());

        var vakcinaSaDozomOptional = vakcine.getVakcine()
                .stream()
                .filter(vakcina -> vakcina.getBrojDoze().getValue().equals(vakcinaDTO.getBrojDoze()))
                .findFirst();
        if (vakcinaSaDozomOptional.isPresent())
            throw new DoseException(String.format("Доза %d је већ дата за сагласност %s.", vakcinaDTO.getBrojDoze(), saglasnost.getId()));

        var vakcina = new Vakcina();
        vakcina.setEkstremitet(RDFString.of(vakcinaDTO.getEkstremitet()));
        vakcina.setNuspojava(RDFString.of(vakcinaDTO.getNuspojava()));
        vakcina.setTip(RDFString.of(vakcinaDTO.getTip()));
        vakcina.setProizvodjac(RDFString.of(vakcinaDTO.getProizvodjac()));
        vakcina.setBrojDoze(RDFInteger.of(vakcinaDTO.getBrojDoze()));
        vakcina.setBrojSerije(RDFString.of(vakcinaDTO.getBrojSerije()));
        vakcina.setDatumDavanja(RDFDate.of(new Date()));
        vakcine.getVakcine().add(vakcina);
        this.saglasnostExistRepository.save(saglasnost);
        this.insertRDFMetadataForSecondHalf(saglasnost);
        this.fusekiRepository.save(saglasnost.getId(), saglasnost);

        return saglasnost;
    }


    public SaglasnostZaSprovodjenjeImunizacije createFirstHalfOfDocument(SaglasnostZaSprovodjenjeImunizacije saglasnost) throws Exception {
        saglasnost.setDatum(RDFDate.of(new Date()));
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

        var interesovanja = interesovanjeService.getAllForUser(saglasnost.provideEmail());
        if(interesovanja.size() == 0 || interesovanja.stream().max(Comparator.comparing(i -> i.getDatum().getValue())).get().getDatumTermina() == null) {
            throw new InteresovanjeNotSubmittedException("Морате поднети интересовање.");
        }

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
            lekarURL = RDF_LEKAR_BASE + lekar.getTelefon().getBrojFiksnog().getValue().strip();
        } else {
            lekarURL = RDF_LEKAR_BASE + lekar.getTelefon().getBrojMobilnog().getValue().strip();
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


    private void insertRDFMetadataForSecondHalf(SaglasnostZaSprovodjenjeImunizacije saglasnostZaSprovodjenjeImunizacije) {
        var vakcinacija = saglasnostZaSprovodjenjeImunizacije.getVakcinacija();
        var zdravstvenaUstanova = vakcinacija.getZdravstvenaUstanova();
        var lekar = vakcinacija.getLekar();
        zdravstvenaUstanova.getNaziv().rdf().property("pred:ustanova").datatype(T_STRING);
        zdravstvenaUstanova.getPunkt().rdf().property("pred:punkt").datatype(T_STRING);
        String lekarURL;
        if (lekar.getTelefon().getBrojFiksnog() != null) {
            lekarURL = RDF_LEKAR_BASE + lekar.getTelefon().getBrojFiksnog().getValue().strip();
        } else {
            lekarURL = RDF_LEKAR_BASE + lekar.getTelefon().getBrojMobilnog().getValue().strip();
        }

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

    public SaglasnostZaSprovodjenjeImunizacije save(SaglasnostZaSprovodjenjeImunizacije saglasnostZaSprovodjenjeImunizacije) throws Exception {
        this.existRepository.save(saglasnostZaSprovodjenjeImunizacije);
        this.fusekiRepository.save(saglasnostZaSprovodjenjeImunizacije.getId(), saglasnostZaSprovodjenjeImunizacije);
        return saglasnostZaSprovodjenjeImunizacije;
    }
}
