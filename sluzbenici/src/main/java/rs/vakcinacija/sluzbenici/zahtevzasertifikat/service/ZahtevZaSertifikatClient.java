package rs.vakcinacija.sluzbenici.zahtevzasertifikat.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.event.DigitalniSertifikatOdobrenEvent;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.event.ZahtevZaSertifikatOdbijenEvent;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.model.KolekcijaZahtevaZaSertifikat;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.model.ZahtevZaSertifikat;

import java.util.UUID;

@FeignClient(value = "zahtevZaSertifikatClient", url = "http://localhost:8081/zahtev-za-sertifikat")
public interface ZahtevZaSertifikatClient {

    @GetMapping(produces = "application/xml")
    KolekcijaZahtevaZaSertifikat readTotal();

    @GetMapping(value = "/za-odobrenje", produces = "application/xml")
    KolekcijaZahtevaZaSertifikat read();

    @GetMapping(value = "/{id}", produces = "application/xml")
    ZahtevZaSertifikat read(@PathVariable UUID id);

    @PostMapping(value = "/{id}/odbij", consumes = "application/xml")
    void reject(@PathVariable UUID id, @RequestBody ZahtevZaSertifikatOdbijenEvent zahtevZaSertifikatOdbijenEvent);

    @PostMapping(value = "/{id}/odobri", consumes = "application/xml")
    void approve(@PathVariable UUID id, @RequestBody DigitalniSertifikatOdobrenEvent digitalniSertifikatOdobrenEvent);
}
