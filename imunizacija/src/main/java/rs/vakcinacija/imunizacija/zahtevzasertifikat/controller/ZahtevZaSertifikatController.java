package rs.vakcinacija.imunizacija.zahtevzasertifikat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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

}
