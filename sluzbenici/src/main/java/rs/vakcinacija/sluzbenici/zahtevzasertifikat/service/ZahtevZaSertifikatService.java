package rs.vakcinacija.sluzbenici.zahtevzasertifikat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.event.DigitalniSertifikatOdobrenEvent;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.event.ZahtevZaSertifikatOdbijenEvent;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.exception.ZahtevZaSertifikatAlreadyProcessed;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.model.KolekcijaZahtevaZaSertifikat;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.model.ZahtevZaSertifikat;
import rs.vakcinacija.zajednicko.email.model.SendEmailRequest;
import rs.vakcinacija.zajednicko.email.service.EmailService;
import rs.vakcinacija.zajednicko.rabbitmq.ServiceBus;

import java.util.Date;
import java.util.UUID;

@Service
public class ZahtevZaSertifikatService {
    private final ZahtevZaSertifikatClient client;
    private final DigitalniSertifikatIssueService digitalniSertifikatIssueService;
    private final EmailService emailService;
    private final ServiceBus serviceBus;

    @Autowired
    public ZahtevZaSertifikatService(ZahtevZaSertifikatClient client,
                                     DigitalniSertifikatIssueService digitalniSertifikatIssueService,
                                     EmailService emailService,
                                     ServiceBus serviceBus) {
        this.client = client;
        this.digitalniSertifikatIssueService = digitalniSertifikatIssueService;
        this.emailService = emailService;
        this.serviceBus = serviceBus;
    }

    public ZahtevZaSertifikat read(UUID id) {
        return client.read(id);
    }

    public KolekcijaZahtevaZaSertifikat read() {
        return client.read();
    }

    // TODO: Actually fetch the email of the request issuer

    public void approve(UUID id) throws Exception {
        // Issue digital certificate and store it
        var request = readForResponse(id);
        var digitalCertificate = digitalniSertifikatIssueService.issueFor(request);
        // Notify user via email that his request has been approved
        emailService.sendEmail(
                new SendEmailRequest("dusanerdeljan99@gmail.com", "Izdavanje Digitalnog sertifikata", "Postovani, odobren Vam je zahtev.")
        );
        // Notify imunizacija service to update link to newly created digital certificate
        serviceBus.publish(
                new DigitalniSertifikatOdobrenEvent(request.getId(), digitalCertificate.getId())
        );
    }

    public void reject(UUID id, String reason) {
        var request = readForResponse(id);
        emailService.sendEmail(
                new SendEmailRequest("dusanerdeljan99@gmail.com", "Odbijanje zahteva za izdavanje Digitalnog sertifikata", "Odbijen Vam je zahtev zbog: " + reason)
        );
        // Maybe notify imunizacija service to update some metadata on the original document?
        serviceBus.publish(
                new ZahtevZaSertifikatOdbijenEvent(request.getId(), reason, new Date())
        );
    }

    private ZahtevZaSertifikat readForResponse(UUID id) {
        var zahtevZaSertifikat = client.read(id);
        if (!zahtevZaSertifikat.getStatus().getValue().equals("KREIRAN")) {
            throw new ZahtevZaSertifikatAlreadyProcessed(String.format("Захтев за сертификат са идентификатором: %s је већ обрађен.", id));
        }
        return zahtevZaSertifikat;
    }
}
