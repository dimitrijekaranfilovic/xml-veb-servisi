package rs.vakcinacija.imunizacija.zahtevzasertifikat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.imunizacija.zahtevzasertifikat.event.DigitalniSertifikatOdobrenEvent;
import rs.vakcinacija.imunizacija.zahtevzasertifikat.event.ZahtevZaSertifikatOdbijenEvent;
import rs.vakcinacija.imunizacija.zahtevzasertifikat.model.KolekcijaZahtevaZaSertifikat;
import rs.vakcinacija.imunizacija.zahtevzasertifikat.model.ZahtevZaSertifikat;
import rs.vakcinacija.imunizacija.zahtevzasertifikat.service.ZahtevZaSertifiaktService;

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
    public ZahtevZaSertifikat write(@RequestBody ZahtevZaSertifikat zahtevZaSertifikat)
            throws Exception {
        return zahtevZaSertifiaktService.create(zahtevZaSertifikat);
    }

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ZahtevZaSertifikat read(@PathVariable UUID id) throws Exception {
        return zahtevZaSertifiaktService.read(id);
    }

    @GetMapping
    public KolekcijaZahtevaZaSertifikat read() throws Exception {
        return KolekcijaZahtevaZaSertifikat.of(zahtevZaSertifiaktService.read());
    }

    @RabbitListener(queues = "DigitalniSertifikatOdobrenEvent")
    public void onRequestApprove(DigitalniSertifikatOdobrenEvent event) {
        log.info(String.format("Odobren zahtev za digitalni sertifikat: '%s', napravljen Digitalni sertifikat: '%s'!", event.getRequestId(), event.getDigitalCertificateId()));
    }

    @RabbitListener(queues = "ZahtevZaSertifikatOdbijenEvent")
    public void onRequestApprove(ZahtevZaSertifikatOdbijenEvent event) {
        log.info(String.format("Zahtev za sertifikat '%s' odbijen zbog: '%s'!", event.getId(), event.getReason()));
    }

}
