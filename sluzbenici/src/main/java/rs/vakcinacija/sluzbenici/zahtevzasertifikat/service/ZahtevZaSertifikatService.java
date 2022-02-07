package rs.vakcinacija.sluzbenici.zahtevzasertifikat.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.model.KolekcijaZahtevaZaSertifikat;

@FeignClient(value = "zahtevZaSertifikatClient", url = "http://localhost:8081/zahtev-za-sertifikat")
public interface ZahtevZaSertifikatService {

    @GetMapping(produces = "application/xml")
    KolekcijaZahtevaZaSertifikat read();
}
