package rs.vakcinacija.sluzbenici.zahtevzasertifikat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.model.*;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.service.DigitalniSertifikatService;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.model.ZahtevZaSertifikat;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.model.RDFString;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DigitalniSertifikatIssueService {
    private final DigitalniSertifikatService digitalniSertifikatService;

    @Autowired
    public DigitalniSertifikatIssueService(DigitalniSertifikatService digitalniSertifikatService) {
        this.digitalniSertifikatService = digitalniSertifikatService;
    }

    public DigitalniSertifikat issueFor(ZahtevZaSertifikat zahtevZaSertifikat) throws Exception {
        var brojSertifikata = RDFString.of(UUID.randomUUID().toString());
        var datumVremeIzdavanja = RDFDate.of(new Date());
        var licneInformacije = buildLicneInformacije(zahtevZaSertifikat);
        var vakcinacija = buildVakcinacija();
        var testovi = buildDefaultTestovi();
        var informacijeOSertifikatu = buildDefaultInformacijeOSertifikatu();

        var sertifikat = new DigitalniSertifikat(brojSertifikata, datumVremeIzdavanja, licneInformacije, vakcinacija, testovi, informacijeOSertifikatu);

        return digitalniSertifikatService.create(sertifikat);
    }

    private Vakcinacija buildVakcinacija() {
        // TODO: Ovo dobavi iz servisa za potvrde o vakcinaciji
        return null;
    }

    private LicneInformacije buildLicneInformacije(ZahtevZaSertifikat zahtevZaSertifikat) {
        // TODO: Izmeni da se ovo dobavlja iz zahteva za sertifikat kad se doda
        var datumRodjenja = RDFDate.of(new Date());
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
