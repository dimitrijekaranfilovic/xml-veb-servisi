package rs.vakcinacija.imunizacija.zahtevzasertifikat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.imunizacija.zahtevzasertifikat.model.ZahtevZaSertifikat;
import rs.vakcinacija.imunizacija.zahtevzasertifikat.repository.ZahtevZaSertifikatExistRepository;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;
import rs.vakcinacija.zajednicko.metadata.repository.FusekiRepository;
import rs.vakcinacija.zajednicko.service.DocumentService;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ZahtevZaSertifiaktService extends DocumentService<ZahtevZaSertifikat> {

    @Autowired
    public ZahtevZaSertifiaktService(ExistRepository<ZahtevZaSertifikat> existRepository,
                                     FusekiRepository<ZahtevZaSertifikat> fusekiRepository) {
        super(existRepository, fusekiRepository);
    }

    public void reject(UUID id, String reason, Date rejectionDate) throws Exception {
        var zahtevZaSertifikat = read(id);
//        var odbijenica = new Odbijenica(RDFString.of(reason), RDFDate.of(rejectionDate));
//        odbijenica.getRazlog().rdf().property("pred:razlog_odbijanja").datatype(T_STRING);
//        odbijenica.getDatumOdbijanja().rdf().property("pred:datum_odbijanja").datatype(T_DATE);
//        zahtevZaSertifikat.setOdbijenica(odbijenica);
        zahtevZaSertifikat.getStatus().setValue("ODBIJEN");
        update(zahtevZaSertifikat);
    }

    public void approve(UUID requestId, UUID certificateId) throws Exception {
        var zahtevZaSertifikat = read(requestId);
        zahtevZaSertifikat.ref("pred:kreirao")
                .uuid(certificateId)
                .type("DigitalniSertifikat")
                .configure();
        zahtevZaSertifikat.getStatus().setValue("PRIHVACEN");
        update(zahtevZaSertifikat);
    }

    public List<ZahtevZaSertifikat> readPending() throws Exception {
        return ((ZahtevZaSertifikatExistRepository) existRepository).readPending();
    }

    public List<ZahtevZaSertifikat> allForUser(String email) throws Exception {
        return existRepository.read((zahtev) -> zahtev.provideEmail().equals(email));
    }

    protected void insertRDFMetadata(ZahtevZaSertifikat zahtevZaSertifikat) {
        var datum = zahtevZaSertifikat.getDatum();
        datum.setProperty(PROP_DATUM);
        datum.setDatatype(T_DATE);

        var podnosilacZahteva = zahtevZaSertifikat.getPodnosilacZahteva();

        String podnosilacUrl = RDF_PACIJENT_BASE + zahtevZaSertifikat.getPodnosilacZahteva()
                .getEmail().getValue();

        var jmbg = zahtevZaSertifikat.getPodnosilacZahteva().getLicniPodaci().getJmbg();
        jmbg.setProperty(PROP_JMBG);
        jmbg.setDatatype(T_STRING);

        podnosilacZahteva.setVocab(VOCAB);
        podnosilacZahteva.setAbout(podnosilacUrl);

        var datumRodjenja = zahtevZaSertifikat.getPodnosilacZahteva().getDatumRodjenja();
        datumRodjenja.setProperty(PROP_DATUM_RODJENJA);
        datumRodjenja.setDatatype(T_DATE);

        var status = zahtevZaSertifikat.getStatus();
        status.setProperty(PROP_STATUS);
        status.setDatatype(T_STRING);

        var brojPasosa = zahtevZaSertifikat.getPodnosilacZahteva().getBrojPasosa();
        brojPasosa.setProperty(PROP_BROJ_PASOSA);
        brojPasosa.setDatatype(T_STRING);

        var mesto = zahtevZaSertifikat.getMesto();
        mesto.setProperty(PROP_MESTO);
        mesto.setDatatype(T_STRING);

        var ime = zahtevZaSertifikat.getPodnosilacZahteva().getLicniPodaci().getIme();
        ime.setProperty(PROP_IME);
        ime.setDatatype(T_STRING);

        var prezime = zahtevZaSertifikat.getPodnosilacZahteva().getLicniPodaci().getPrezime();
        prezime.setProperty(PROP_PREZIME);
        prezime.setDatatype(T_STRING);
    }

    public String getHTMLRepresentation(UUID id) throws Exception {
        ZahtevZaSertifikat zahtevZaSertifikat = read(id);
        return generateHTML(zahtevZaSertifikat, "src/main/resources/xslt/zahtev_za_sertifikat.xsl");
    }

    public ByteArrayInputStream getPDFRepresentation(UUID id) throws Exception {
        ZahtevZaSertifikat zahtevZaSertifikat = read(id);
//        var razlog = zahtevZaSertifikat.getRazlogZaPodnosenjeZahteva().getValue();
//        razlog = razlog.replaceAll("<[^>]*>", "");
//        zahtevZaSertifikat.setRazlogZaPodnosenjeZahteva(RDFString.of(razlog));
        return generatePDF(zahtevZaSertifikat, "src/main/resources/xsl-fo/zahtev_za_sertifikat_fo.xsl");
    }
}
