package rs.vakcinacija.sluzbenici.zahtevzasertifikat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import rs.vakcinacija.sluzbenici.config.email.EmailClient;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.model.DigitalniSertifikat;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.event.DigitalniSertifikatOdobrenEvent;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.event.ZahtevZaSertifikatOdbijenEvent;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.exception.ZahtevZaSertifikatAlreadyProcessedException;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.model.KolekcijaZahtevaZaSertifikat;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.model.ZahtevZaSertifikat;
import rs.vakcinacija.zajednicko.email.model.SendEmailRequest;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.UUID;

@Service
public class ZahtevZaSertifikatService {
    private final ZahtevZaSertifikatClient client;
    private final DigitalniSertifikatIssueService digitalniSertifikatIssueService;
    private final EmailClient emailService;

    @Autowired
    public ZahtevZaSertifikatService(ZahtevZaSertifikatClient client,
                                     DigitalniSertifikatIssueService digitalniSertifikatIssueService,
                                     EmailClient emailService) {
        this.client = client;
        this.digitalniSertifikatIssueService = digitalniSertifikatIssueService;
        this.emailService = emailService;
    }

    public ZahtevZaSertifikat read(UUID id) {
        return client.read(id);
    }

    public KolekcijaZahtevaZaSertifikat read() {
        return client.read();
    }

    public void approve(UUID id) throws Exception {
        // Issue digital certificate and store it
        var request = readForResponse(id);
        var digitalCertificate = digitalniSertifikatIssueService.issueFor(request);
        // Notify user via email that his request has been approved
        emailService.sendEmail(
                new SendEmailRequest(request.provideEmail(), "Издавање Дигиталног сертификата", buildApproveMessage(request, digitalCertificate))
        );
        // Notify imunizacija service to update link to newly created digital certificate
        client.approve(
                id,
                new DigitalniSertifikatOdobrenEvent(digitalCertificate.getId())
        );
    }

    public void reject(UUID id, String reason) {
        var request = readForResponse(id);
        emailService.sendEmail(
                new SendEmailRequest(request.provideEmail(), "Одбијање захтева за издавање Дигиталног сертификата",  buildRejectMessage(request, reason))
        );
        // Maybe notify imunizacija service to update some metadata on the original document?
        client.reject(
                id,
                new ZahtevZaSertifikatOdbijenEvent(reason, new Date())
        );
    }

    private ZahtevZaSertifikat readForResponse(UUID id) {
        var zahtevZaSertifikat = client.read(id);
        if (!zahtevZaSertifikat.getStatus().getValue().equals("KREIRAN")) {
            throw new ZahtevZaSertifikatAlreadyProcessedException(String.format("Захтев за сертификат са идентификатором: %s је већ обрађен.", id));
        }
        return zahtevZaSertifikat;
    }

    private String buildApproveMessage(ZahtevZaSertifikat zahtevZaSertifikat, DigitalniSertifikat digitalniSertifikat) {
        var ime = zahtevZaSertifikat.getPodnosilacZahteva().getLicniPodaci().getIme().getValue();
        var prezime = zahtevZaSertifikat.getPodnosilacZahteva().getLicniPodaci().getPrezime().getValue();
        return String.format("Поштовани %s %s,\n\n", ime, prezime) +
                String.format("Одобрен Вам је Дигитални сертификат на основу захтева %s.\n\n", digitalniSertifikat.getId()) +
                String.format("Дигитални сертификат можете погледати и преузети кликом на <a href='http://localhost:3001/#/pregled/digitalni-sertifikat/%s' >линк</a>.\n\n", digitalniSertifikat.getId()) +
                "Срдачан поздрав,\nВаш портал за имунизацију\n\n";
    }

    private String buildRejectMessage(ZahtevZaSertifikat zahtevZaSertifikat, String razlog) {
        var ime = zahtevZaSertifikat.getPodnosilacZahteva().getLicniPodaci().getIme().getValue();
        var prezime = zahtevZaSertifikat.getPodnosilacZahteva().getLicniPodaci().getPrezime().getValue();
        return String.format("Поштовани %s %s,\n\n", ime, prezime) +
                String.format("Ваш захтев за издавање Дигиталног сертификата са идентификатором %s је одбијен из следећих разлога:\n\n", zahtevZaSertifikat.getId()) +
                razlog +
                "\n\nСрдачан поздрав,\nВаш портал за имунизацију\n\n";

    }

    public String html(UUID id) {
        return client.html(id);
    }

    public InputStreamResource pdf(UUID id) {
        return new InputStreamResource(
                new ByteArrayInputStream(
                        client.pdf(id)
                )
        );
    }
}
