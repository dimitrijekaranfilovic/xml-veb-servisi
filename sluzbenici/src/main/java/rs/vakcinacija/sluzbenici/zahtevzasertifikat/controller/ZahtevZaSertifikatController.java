package rs.vakcinacija.sluzbenici.zahtevzasertifikat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.dto.odbijzahtev.OdbijZahtevZaSertifikatRequest;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.model.KolekcijaZahtevaZaSertifikat;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.model.ZahtevZaSertifikat;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.service.ZahtevZaSertifikatService;

import java.util.UUID;

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

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ZahtevZaSertifikat read(@PathVariable UUID id) {
        return zahtevZaSertifikatService.read(id);
    }

    @PostMapping(value = "/{id}/odobri", consumes = MediaType.ALL_VALUE)
    public void acceptRequest(@PathVariable UUID id) throws Exception {
        zahtevZaSertifikatService.approve(id);
    }

    @PostMapping(value = "/{id}/odbij", consumes = MediaType.ALL_VALUE)
    public void rejectRequest(@PathVariable UUID id, @RequestBody OdbijZahtevZaSertifikatRequest request) {
        zahtevZaSertifikatService.reject(id, request.getRazlog());
    }
}
