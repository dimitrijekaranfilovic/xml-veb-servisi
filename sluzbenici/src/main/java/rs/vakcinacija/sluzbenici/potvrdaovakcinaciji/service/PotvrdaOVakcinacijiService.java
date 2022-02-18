package rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.model.DigitalniSertifikat;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model.Doze;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model.LicneInformacije;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model.PotvrdaOVakcinaciji;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model.Vakcinacija;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.repository.PotvrdaOVakcinacijiExistRepository;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.repository.PotvrdaOVakcinacijiFusekiRepository;
import rs.vakcinacija.sluzbenici.saglasnost.model.SaglasnostZaSprovodjenjeImunizacije;
import rs.vakcinacija.sluzbenici.saglasnost.service.SaglasnostClient;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.exception.CitizenHasNoVaccinationCertificateException;
import rs.vakcinacija.zajednicko.model.Doza;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.model.RDFInteger;
import rs.vakcinacija.zajednicko.model.RDFString;
import rs.vakcinacija.zajednicko.service.DocumentService;
import rs.vakcinacija.zajednicko.service.QRCodeService;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class PotvrdaOVakcinacijiService extends DocumentService<PotvrdaOVakcinaciji> {

    private final QRCodeService qrCodeService;
    private final SaglasnostClient saglasnostClient;

    @Autowired
    public PotvrdaOVakcinacijiService(PotvrdaOVakcinacijiExistRepository potvrdaOVakcinacijiExistRepository,
                                      PotvrdaOVakcinacijiFusekiRepository potvrdaOVakcinacijiFusekiRepository, QRCodeService qrCodeService, SaglasnostClient saglasnostClient) {
        super(potvrdaOVakcinacijiExistRepository, potvrdaOVakcinacijiFusekiRepository);
        this.qrCodeService = qrCodeService;
        this.saglasnostClient = saglasnostClient;
    }

    public Optional<PotvrdaOVakcinaciji> readByCitizenEmail(String email) throws Exception {
        return existRepository.read(p -> p.provideEmail().equals(email)).stream().findFirst();
    }

    public void onIssueCertificate(PotvrdaOVakcinaciji potvrdaOVakcinaciji, DigitalniSertifikat sertifikat) throws Exception {
        potvrdaOVakcinaciji.ref("pred:kreirao")
                .entity(sertifikat)
                .type(DigitalniSertifikat.class)
                .configure();
        super.update(potvrdaOVakcinaciji);
        log.info(String.format("Potvrda o vakcinaciji: %s izmenjena tako da ima vezu ka sertifikatu: %s", potvrdaOVakcinaciji.getId(), sertifikat.getId()));
    }

    protected void insertRDFMetadata(PotvrdaOVakcinaciji potvrdaOVakcinaciji) {
        var qrKod = potvrdaOVakcinaciji.getQrKod();
        var sifra = potvrdaOVakcinaciji.getSifraPotvrde();
        var datumIzdavanja = potvrdaOVakcinaciji.getDatumIzdavanja();

        var licneInformacije = potvrdaOVakcinaciji.getLicneInformacije();
        var ime = licneInformacije.getIme();
        var prezime = licneInformacije.getPrezime();
        var jmbg = licneInformacije.getJmbg();
        var pol = licneInformacije.getPol();
        var vakcinacija = potvrdaOVakcinaciji.getVakcinacija();

        var ustanova = vakcinacija.getUstanova();

        sifra.rdf().property(PROP_SIFRA_DOKUMENTA).datatype(T_STRING);
        datumIzdavanja.rdf().property(PROP_DATUM_IZDAVANJA).datatype(T_DATE);
        ustanova.rdf().property("pred:ustanova").datatype(T_STRING);

        var pacijentUrl = RDF_PACIJENT_BASE + potvrdaOVakcinaciji.getEmail().getValue();
        ime.rdf().property(PROP_IME).datatype(T_STRING);
        prezime.rdf().property(PROP_PREZIME).datatype(T_STRING);
        jmbg.rdf().property(PROP_JMBG).datatype(T_STRING);
        pol.rdf().property(PROP_POL).datatype(T_STRING);

        licneInformacije.rdf().vocab(VOCAB).about(pacijentUrl);

        var vakcinaUrl = RDF_VAKCINA_BASE + vakcinacija.getNazivVakcine().getValue();

        qrKod.rdf().property(PROP_QR_KOD).datatype(T_STRING);
        potvrdaOVakcinaciji.getEmail().rdf().property(PROP_EMAIL).datatype(T_STRING);
    }

    public PotvrdaOVakcinaciji issueConfirmation(SaglasnostZaSprovodjenjeImunizacije saglasnostZaSprovodjenjeImunizacije) throws Exception {
        var vakcine = saglasnostZaSprovodjenjeImunizacije.getVakcinacija().getVakcine().getVakcine();
        var email = saglasnostZaSprovodjenjeImunizacije.getPacijent().getLicneInformacije().getKontakt().getEmail().getValue();
        PotvrdaOVakcinaciji potvrdaOVakcinaciji;
        if (vakcine.size() == 1) {
            //kreiraj novu potvrdu
            var potvrdaId = UUID.randomUUID();
            potvrdaOVakcinaciji = new PotvrdaOVakcinaciji();

            potvrdaOVakcinaciji.setSifraPotvrde(RDFString.of(potvrdaId.toString()));
            potvrdaOVakcinaciji.setDatumIzdavanja(RDFDate.of(new Date()));
            potvrdaOVakcinaciji.setQrKod(this.createQrKod(potvrdaId));
            potvrdaOVakcinaciji.setLicneInformacije(this.createLicneInformacije(saglasnostZaSprovodjenjeImunizacije));
            potvrdaOVakcinaciji.setEmail(RDFString.of(email));
            potvrdaOVakcinaciji.setVakcinacija(this.createVakcinacija(saglasnostZaSprovodjenjeImunizacije));
            potvrdaOVakcinaciji.setId(potvrdaId);

        } else {
            //azuriraj postojecu
            potvrdaOVakcinaciji = this
                    .readByCitizenEmail(email)
                    .orElseThrow(() -> new CitizenHasNoVaccinationCertificateException("Не постоји потврда за дату сагласност."));

            potvrdaOVakcinaciji.setDatumIzdavanja(RDFDate.of(new Date()));
            potvrdaOVakcinaciji.setVakcinacija(this.createVakcinacija(saglasnostZaSprovodjenjeImunizacije));

        }

        this.updateReferences(potvrdaOVakcinaciji, saglasnostZaSprovodjenjeImunizacije);
        saglasnostClient.save(saglasnostZaSprovodjenjeImunizacije);
        potvrdaOVakcinaciji = this.create(potvrdaOVakcinaciji);
        return potvrdaOVakcinaciji;
    }

    private void updateReferences(PotvrdaOVakcinaciji potvrdaOVakcinaciji, SaglasnostZaSprovodjenjeImunizacije saglasnostZaSprovodjenjeImunizacije) {
        var referencaPotvrda = potvrdaOVakcinaciji.getReference().getReference()
                .stream().filter(r ->
                        r.getProperty().equals("pred:na_osnovu_saglasnosti")
                )
                .findFirst();
        var referencaSaglasnost = saglasnostZaSprovodjenjeImunizacije.getReference()
                .getReference().stream().filter(r -> r.getProperty().equals("pred:generisana_potvrda"))
                .findFirst();


        if(referencaPotvrda.isEmpty()){
            potvrdaOVakcinaciji
                    .ref("pred:na_osnovu_saglasnosti")
                    .entity(saglasnostZaSprovodjenjeImunizacije)
                    .type(SaglasnostZaSprovodjenjeImunizacije.class)
                    .configure();
        }

        if(referencaSaglasnost.isEmpty()){
            saglasnostZaSprovodjenjeImunizacije
                    .ref("pred:generisana_potvrda")
                    .entity(potvrdaOVakcinaciji)
                    .type(PotvrdaOVakcinaciji.class)
                    .configure();
        }
    }

    private LicneInformacije createLicneInformacije(SaglasnostZaSprovodjenjeImunizacije saglasnostZaSprovodjenjeImunizacije) {
        var licniPodaci = new LicneInformacije();
        var licneInformacije = saglasnostZaSprovodjenjeImunizacije.getPacijent().getLicneInformacije();

        var srpskiDrzavljanin = licneInformacije.getDrzavljanstvo().getSrpskiDrzavljanin();
        if (srpskiDrzavljanin != null) {
            licniPodaci.setJmbg(RDFString.of(srpskiDrzavljanin.getJmbg().getValue()));
        } else {
            var straniDrzavljanin = licneInformacije.getDrzavljanstvo().getStraniDrzavljanin();
            //TODO: vidi je l ovo ovako treba
            String s = String.format("%s,%s", straniDrzavljanin.getBrojPasosa().getValue(), straniDrzavljanin.getNazivDrzavljanstva().getValue());
            licniPodaci.setJmbg(RDFString.of(s));
        }
        licniPodaci.setPol(RDFString.of(licneInformacije.getPol().getValue()));
        licniPodaci.setIme(RDFString.of(licneInformacije.getPunoIme().getIme().getValue()));
        licniPodaci.setPrezime(RDFString.of(licneInformacije.getPunoIme().getPrezime().getValue()));
        licniPodaci.setDatumRodjenja(RDFDate.of(saglasnostZaSprovodjenjeImunizacije.getPacijent().getLicneInformacije().getDatumRodjenja().getValue()));
        return licniPodaci;
    }

    private Vakcinacija createVakcinacija(SaglasnostZaSprovodjenjeImunizacije saglasnostZaSprovodjenjeImunizacije) {
        var vakcinacija = new Vakcinacija();
        var doze = new Doze();

        var sb = new StringBuilder();

        for(var vakcina: saglasnostZaSprovodjenjeImunizacije.getVakcinacija().getVakcine().getVakcine()) {
            var brojDoze = vakcina.getBrojDoze().getValue();
            var proizvodjac = vakcina.getProizvodjac().getValue();

            sb.append(brojDoze).append(".").append(proizvodjac).append(" ");
            var doza = new Doza();

            doza.setBrojDoze(RDFInteger.of(brojDoze));
            doza.setDatumDavanja(RDFDate.of(vakcina.getDatumDavanja().getValue()));
            doza.setBrojSerije(RDFString.of(vakcina.getBrojSerije().getValue()));

            doze.getDoze().add(doza);
        }

        vakcinacija.setDoze(doze);
        vakcinacija.setUstanova(RDFString.of(saglasnostZaSprovodjenjeImunizacije.getVakcinacija().getZdravstvenaUstanova().getNaziv().getValue()));
        vakcinacija.setNazivVakcine(RDFString.of(sb.toString()));

        return vakcinacija;
    }

    private RDFString createQrKod(UUID id) throws Exception {
        var qrKodUrl = "http://localhost:3001/#/pregled/potvrda-o-vakcinaciji/" + id;
        return RDFString.of(qrCodeService.generateQRCodeBase64String(qrKodUrl));
    }
}
