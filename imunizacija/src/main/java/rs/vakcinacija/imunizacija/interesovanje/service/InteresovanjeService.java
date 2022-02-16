package rs.vakcinacija.imunizacija.interesovanje.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import rs.vakcinacija.imunizacija.config.email.EmailClient;
import rs.vakcinacija.imunizacija.interesovanje.event.InteresovanjePodnetoEvent;
import rs.vakcinacija.imunizacija.interesovanje.model.Interesovanje;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;
import rs.vakcinacija.zajednicko.email.model.SendEmailRequest;
import rs.vakcinacija.zajednicko.email.service.EmailService;
import rs.vakcinacija.zajednicko.metadata.repository.FusekiRepository;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.service.DocumentService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class InteresovanjeService extends DocumentService<Interesovanje> {

    private final EmailClient emailService;
    private final VakcinacioniPunktClient vakcinacioniPunktClient;

    @Autowired
    public InteresovanjeService(ExistRepository<Interesovanje> existRepository,
                                FusekiRepository<Interesovanje> fusekiRepository,
                                EmailClient emailService,
                                VakcinacioniPunktClient vakcinacioniPunktClient) {
        super(existRepository, fusekiRepository);
        this.emailService = emailService;
        this.vakcinacioniPunktClient = vakcinacioniPunktClient;
    }

    public List<Interesovanje> getAllForUser(String email) throws Exception {
        return existRepository.read((doc) -> doc.getLicneInformacije().getKontakt()
                .getEmail().getValue().equals(email));
    }

    public Interesovanje submitInteresovanjeAndSendEmail(Interesovanje interesovanje) throws Exception {
        interesovanje.setDatum(RDFDate.of(new Date()));
        sendInteresovanjeRecievedMessage(interesovanje);
        var retVal = this.create(interesovanje);
        this.vakcinacioniPunktClient.onInteresetSubmitted(
                new InteresovanjePodnetoEvent(interesovanje.getOdabranaLokacijaPrimanjaVakcine().getValue(),
                        interesovanje.getOdabraniProizvodjaci().getProizvodjaci(),
                        interesovanje.getLicneInformacije().getKontakt().getEmail().getValue(),
                        interesovanje.getId()));
        return retVal;
    }

    protected void insertRDFMetadata(Interesovanje interesovanje) {
        var datum = interesovanje.getDatum();
        datum.setProperty(PROP_DATUM);
        datum.setDatatype(T_DATE);

        var mesto = interesovanje.getOdabranaLokacijaPrimanjaVakcine();
        mesto.setProperty(PROP_MESTO);
        mesto.setDatatype(T_STRING);

        var autogeneratedId = UUID.randomUUID();
        interesovanje.setId(autogeneratedId);

        var podnosilacZahteva = interesovanje.getLicneInformacije();

        String podnosilacUrl = RDF_PACIJENT_BASE + interesovanje.getLicneInformacije().getKontakt()
                .getEmail().getValue();

        interesovanje.setVocab(VOCAB);
        interesovanje.setAbout(RDF_INTERESOVANJE_BASE + autogeneratedId);
        interesovanje.setRel(PROP_PODNESEN_OD);
        interesovanje.setTypeof(T_INTERESOVANJE);
        interesovanje.setHref(podnosilacUrl);

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


    @Async
    public void sendInteresovanjeRecievedMessage(Interesovanje interesovanje) {
        var to = interesovanje.getLicneInformacije().getKontakt().getEmail().getValue();
        var subject = "Обавештење о интересовању за вакцинацију";
        var sb = new StringBuilder();
        sb.append("Поштовани, \n");
        sb.append("\nВаше интересовање је успешно исказано.\n");
        sb.append("Добићете обавештење о тачном датуму вакцинације чим вам доделимо први слободан термин.\n");
        sb.append("У наставку се налазе ваши подаци које сте унели приликом исказивања интересовања: \n");
        sb.append("\n\tДржављанство: ")
                .append(interesovanje.getLicneInformacije().getCyrilicDrzavljanstvo()).append("\n");
        sb.append("\n\tЈМБГ: ")
                .append(interesovanje.getLicneInformacije().getJmbg().getValue())
                .append("\n");
        sb.append("\n\tИме: ").append(interesovanje.getLicneInformacije().getImePrezime().getIme().getValue())
                .append("\n");
        sb.append("\n\tПрезиме: ").append(interesovanje.getLicneInformacije().getImePrezime().getPrezime().getValue())
                .append("\n");
        sb.append("\n\tАдреса електронске поште: ").append(
                        interesovanje.getLicneInformacije().getKontakt().getEmail().getValue())
                .append("\n");
        sb.append("\n\tБрој мобилног телефона: ")
                .append(interesovanje.getLicneInformacije().getKontakt().getBrojMobilnog().getValue())
                .append("\n");
        sb.append("\n\tБрој фиксног телефона: ")
                .append(interesovanje.getLicneInformacije().getKontakt().getBrojFiksnog().getValue())
                .append("\n");
        sb.append("\n\tЛокација где желите да примите вакцину: ")
                .append(interesovanje.getOdabranaLokacijaPrimanjaVakcine().getValue())
                .append("\n");
        sb.append("\n\tОдабрани произвођачи: \n");
        interesovanje.getOdabraniProizvodjaci().getProizvodjaci().forEach(p -> sb.append("\n\t\t")
                .append(p).append("\n"));
        sb.append("\n\tДа ли сте добровољни давалац крви: ")
                .append(Boolean.TRUE.equals(
                        interesovanje.getLicneInformacije().getDavalacKrvi().getValue()) ? "Да" : "Не")
                .append("\n");
        sb.append("\n\n\nСрдачан поздрав,\n");
        sb.append("Ваш портал за имунизацију\n");

        emailService.sendEmail(new SendEmailRequest(to, subject, sb.toString()));
    }
}
