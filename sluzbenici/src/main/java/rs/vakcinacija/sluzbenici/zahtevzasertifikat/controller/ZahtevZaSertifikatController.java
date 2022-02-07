package rs.vakcinacija.sluzbenici.zahtevzasertifikat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.model.KolekcijaZahtevaZaSertifikat;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.service.ZahtevZaSertifikatService;

@RestController
@RequestMapping(value = "zahtev-za-sertifikat")
public class ZahtevZaSertifikatController {
    private final ZahtevZaSertifikatService zahtevZaSertifikatService;

    @Autowired
    public ZahtevZaSertifikatController(ZahtevZaSertifikatService zahtevZaSertifikatService) {
        this.zahtevZaSertifikatService = zahtevZaSertifikatService;
    }

    @GetMapping
    public KolekcijaZahtevaZaSertifikat read() {
        return zahtevZaSertifikatService.read();
    }
}
