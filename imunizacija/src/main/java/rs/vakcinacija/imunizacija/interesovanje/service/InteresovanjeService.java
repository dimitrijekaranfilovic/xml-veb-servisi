package rs.vakcinacija.imunizacija.interesovanje.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.imunizacija.interesovanje.model.Interesovanje;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;
import rs.vakcinacija.zajednicko.metadata.repository.FusekiRepository;
import rs.vakcinacija.zajednicko.service.DocumentService;

@Service
public class InteresovanjeService extends DocumentService<Interesovanje> {
    @Autowired
    public InteresovanjeService(ExistRepository<Interesovanje> existRepository,
                                FusekiRepository<Interesovanje> fusekiRepository) {
        super(existRepository, fusekiRepository);
    }

    protected void insertRDFMetadata(Interesovanje interesovanje) {
        var datum = interesovanje.getDatum();
        datum.setProperty(PROP_DATUM);
        datum.setDatatype(T_DATE);

        var mesto = interesovanje.getOdabranaLokacijaPrimanjaVakcine();
        mesto.setProperty(PROP_MESTO);
        mesto.setDatatype(T_STRING);

        var podnosilacZahteva = interesovanje.getLicneInformacije();

        if (podnosilacZahteva.getJmbg() != null) {
            String podnosilacUrl = RDF_PACIJENT_BASE + podnosilacZahteva.getJmbg().getValue();
            podnosilacZahteva.setVocab(VOCAB);
            podnosilacZahteva.setAbout(podnosilacUrl);

            var jmbg = podnosilacZahteva.getJmbg();
            jmbg.setProperty(PROP_JMBG);
            jmbg.setDatatype(T_STRING);

            var ime = interesovanje.getLicneInformacije().getImePrezime().getIme();
            ime.setProperty(PROP_IME);
            ime.setDatatype(T_STRING);

            var prezime = interesovanje.getLicneInformacije().getImePrezime().getPrezime();
            prezime.setProperty(PROP_PREZIME);
            prezime.setDatatype(T_STRING);

            var davalac = interesovanje.getLicneInformacije().getDavalacKrvi();
            davalac.setProperty(PROP_DAVALAC_KRVI);
            davalac.setDatatype(T_BOOLEAN);

            var brojFiksnog = interesovanje.getLicneInformacije().getKontakt().getBrojFiksnog();
            brojFiksnog.setProperty(PROP_BROJ_FIKSNOG);
            brojFiksnog.setDatatype(T_STRING);

            var brojMobilnog = interesovanje.getLicneInformacije().getKontakt().getBrojMobilnog();
            brojMobilnog.setProperty(PROP_BROJ_MOBILNOG);
            brojMobilnog.setDatatype(T_STRING);

            var email = interesovanje.getLicneInformacije().getKontakt().getEmail();
            email.setProperty(PROP_EMAIL);
            email.setDatatype(T_STRING);
        }
    }
}
