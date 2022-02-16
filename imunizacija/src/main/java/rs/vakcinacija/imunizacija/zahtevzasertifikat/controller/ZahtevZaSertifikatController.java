package rs.vakcinacija.imunizacija.zahtevzasertifikat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.imunizacija.zahtevzasertifikat.event.DigitalniSertifikatOdobrenEvent;
import rs.vakcinacija.imunizacija.zahtevzasertifikat.event.ZahtevZaSertifikatOdbijenEvent;
import rs.vakcinacija.imunizacija.zahtevzasertifikat.model.KolekcijaZahtevaZaSertifikat;
import rs.vakcinacija.imunizacija.zahtevzasertifikat.model.ZahtevZaSertifikat;
import rs.vakcinacija.imunizacija.zahtevzasertifikat.service.ZahtevZaSertifiaktService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "zahtev-za-sertifikat")
public class ZahtevZaSertifikatController {

    private final ZahtevZaSertifiaktService zahtevZaSertifiaktService;

    @Autowired
    public ZahtevZaSertifikatController(ZahtevZaSertifiaktService zahtevZaSertifiaktService) {
        this.zahtevZaSertifiaktService = zahtevZaSertifiaktService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ZahtevZaSertifikat write(@RequestBody ZahtevZaSertifikat zahtevZaSertifikat)
            throws Exception {
        return zahtevZaSertifiaktService.create(zahtevZaSertifikat);
    }

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ZahtevZaSertifikat read(@PathVariable UUID id) throws Exception {
        return zahtevZaSertifiaktService.read(id);
    }

    @GetMapping(value = "all/{email}")
    public KolekcijaZahtevaZaSertifikat readAllForUser(@PathVariable String email) throws Exception {
        return KolekcijaZahtevaZaSertifikat.of(zahtevZaSertifiaktService.allForUser(email));
    }

    @GetMapping
    public KolekcijaZahtevaZaSertifikat read() throws Exception {
        var kolekcija = KolekcijaZahtevaZaSertifikat.of(zahtevZaSertifiaktService.read());
        log.info("Lista je null: " + (kolekcija.getZahteviZaSertifikat() == null));
        return kolekcija;
    }

    @GetMapping(value = "/za-odobrenje")
    public KolekcijaZahtevaZaSertifikat readPending() throws Exception {
        return KolekcijaZahtevaZaSertifikat.of(zahtevZaSertifiaktService.readPending());
    }

    @RabbitListener(queues = "DigitalniSertifikatOdobrenEvent")
    public void onRequestApproved(DigitalniSertifikatOdobrenEvent event) throws Exception {
        log.info(String.format("Odobren zahtev za digitalni sertifikat: '%s', napravljen Digitalni sertifikat: '%s'!", event.getRequestId(), event.getDigitalCertificateId()));
        zahtevZaSertifiaktService.approve(event.getRequestId(), event.getDigitalCertificateId());
    }

    @RabbitListener(queues = "ZahtevZaSertifikatOdbijenEvent")
    public void onRequestRejected(ZahtevZaSertifikatOdbijenEvent event) throws Exception {
        log.info(String.format("Zahtev za sertifikat '%s' odbijen zbog: '%s'!", event.getId(), event.getReason()));
        zahtevZaSertifiaktService.reject(event.getId(), event.getReason(), event.getRejectionDate());
    }
}
