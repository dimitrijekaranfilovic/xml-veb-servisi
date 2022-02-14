package rs.vakcinacija.sluzbenici.zahtevzasertifikat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.model.*;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.service.DigitalniSertifikatService;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model.PotvrdaOVakcinaciji;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.service.PotvrdaOVakcinacijiService;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.exception.CitizenHasNoVaccinationCertificateException;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.model.ZahtevZaSertifikat;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.model.RDFInteger;
import rs.vakcinacija.zajednicko.model.RDFString;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DigitalniSertifikatIssueService {
    private final DigitalniSertifikatService digitalniSertifikatService;
    private final PotvrdaOVakcinacijiService potvrdaOVakcinacijiService;

    @Autowired
    public DigitalniSertifikatIssueService(DigitalniSertifikatService digitalniSertifikatService, PotvrdaOVakcinacijiService potvrdaOVakcinacijiService) {
        this.digitalniSertifikatService = digitalniSertifikatService;
        this.potvrdaOVakcinacijiService = potvrdaOVakcinacijiService;
    }

    public DigitalniSertifikat issueFor(ZahtevZaSertifikat zahtevZaSertifikat) throws Exception {
        var brojSertifikata = RDFString.of(UUID.randomUUID().toString());
        var datumVremeIzdavanja = RDFDate.of(new Date());
        var licneInformacije = buildLicneInformacije(zahtevZaSertifikat);

        var jmbg = zahtevZaSertifikat.getPodnosilacZahteva().getLicniPodaci().getJmbg().getValue();
        var potvrdaOVakcinaciji = potvrdaOVakcinacijiService
                .readForCitizen(jmbg)
                .orElseThrow(() -> new CitizenHasNoVaccinationCertificateException("Грађанин нема ни једну потврду о вакцинацији која је непоходна за идавање Дигиталног сертификата."));
        var vakcinacija = buildVakcinacija(potvrdaOVakcinaciji);

        var testovi = buildDefaultTestovi();
        var informacijeOSertifikatu = buildDefaultInformacijeOSertifikatu();

        var sertifikat = new DigitalniSertifikat(brojSertifikata, datumVremeIzdavanja, licneInformacije, vakcinacija, testovi, informacijeOSertifikatu);
        sertifikat.ref("pred:na_zahtev")
                  .entity(zahtevZaSertifikat)
                  .type(ZahtevZaSertifikat.class)
                  .configure();
        sertifikat.ref("pred:na_osnovu_potvrde")
                  .entity(potvrdaOVakcinaciji)
                  .type(PotvrdaOVakcinaciji.class)
                  .configure();

        sertifikat = digitalniSertifikatService.create(sertifikat);
        potvrdaOVakcinacijiService.onIssueCertificate(potvrdaOVakcinaciji, sertifikat);
        return sertifikat;
    }

    private Vakcinacija buildVakcinacija(PotvrdaOVakcinaciji potvrdaOVakcinaciji) {
        var nazivVakcine = potvrdaOVakcinaciji.getVakcinacija().getNazivVakcine().getValue();
        var ustanova = potvrdaOVakcinaciji.getVakcinacija().getUstanova().getValue();
        var dozeVakcina = potvrdaOVakcinaciji.getVakcinacija().getDoze().getDoze().stream().map(doza -> {
            return new DozaVakcine(RDFInteger.of(doza.getBrojDoze().getValue()),
                                   RDFDate.of(doza.getDatumDavanja().getValue()),
                                   RDFString.of(doza.getBrojSerije().getValue()),
                                   RDFString.of(nazivVakcine),
                                   RDFString.of(nazivVakcine),
                                   RDFString.of(ustanova));
        }).collect(Collectors.toList());
        return new Vakcinacija(dozeVakcina);
    }

    private LicneInformacije buildLicneInformacije(ZahtevZaSertifikat zahtevZaSertifikat) {
        var datumRodjenja = RDFDate.of(zahtevZaSertifikat.getPodnosilacZahteva().getDatumRodjenja().getValue());
        var brojPasosa = RDFString.of(zahtevZaSertifikat.getPodnosilacZahteva().getBrojPasosa().getValue());
        var jmbg = RDFString.of(zahtevZaSertifikat.getPodnosilacZahteva().getLicniPodaci().getJmbg().getValue());
        var pol = RDFString.of(zahtevZaSertifikat.getPodnosilacZahteva().getLicniPodaci().getPol().getValue());
        var ime = RDFString.of(zahtevZaSertifikat.getPodnosilacZahteva().getLicniPodaci().getIme().getValue());
        var prezime = RDFString.of(zahtevZaSertifikat.getPodnosilacZahteva().getLicniPodaci().getPrezime().getValue());
        return new LicneInformacije(ime, prezime, jmbg, pol, datumRodjenja, brojPasosa);
    }

    private InformacijeOSertifikatu buildDefaultInformacijeOSertifikatu() {
        var qrKod = RDFString.of("http://localhost:3001/digitalni-sertifikat/neki-id");
        var digitalniPotipis = buildDefaultDigitalniPotpis();
        return new InformacijeOSertifikatu(qrKod, digitalniPotipis);
    }

    private DigitalniPotpis buildDefaultDigitalniPotpis() {
        var drzava = RDFString.of("Republika Srbija");
        var datum = RDFDate.of(new Date());
        return new DigitalniPotpis(drzava, datum);
    }

    private Testovi buildDefaultTestovi() {
        return new Testovi(List.of(
                buildTest("SARS-CoV-2 RT", "Real-time PCR"),
                buildTest("SARS-CoV-2 Ag-RDT", "Antiged Rapid Detection test"),
                buildTest("SARS-CoV-2 RBD S-Protein", "Immunoglobulin G (IgG) test")
        ));
    }

    private Test buildTest(String naziv, String opis) {
        return new Test(
                RDFString.of(naziv),
                RDFString.of(opis),
                RDFString.of("N/A"),
                RDFString.of("N/A"),
                RDFDate.of(new Date()),
                RDFDate.of(new Date()),
                RDFString.of("N/A"),
                RDFString.of("N/A")
        );
    }
}
