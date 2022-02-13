package rs.vakcinacija.sluzbenici.zahtevzasertifikat.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.model.KolekcijaZahtevaZaSertifikat;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.model.ZahtevZaSertifikat;

import java.util.UUID;

@FeignClient(value = "zahtevZaSertifikatClient", url = "http://localhost:8081/zahtev-za-sertifikat")
public interface ZahtevZaSertifikatClient {

    @GetMapping(produces = "application/xml")
    KolekcijaZahtevaZaSertifikat read();

    @GetMapping(value = "/{id}", produces = "application/xml")
    ZahtevZaSertifikat read(@PathVariable UUID id);
}
