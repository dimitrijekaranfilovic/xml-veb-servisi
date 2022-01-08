package rs.vakcinacija.imunizacija.zahtevzasertifikat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.imunizacija.zahtevzasertifikat.model.ZahtevZaSertifikat;
import rs.vakcinacija.imunizacija.zahtevzasertifikat.repository.ZahtevZaSertifikatRepository;

import java.util.UUID;

@RestController
@RequestMapping(value = "zahtev-za-sertifikat")
public class ZahtevZaSertifikatController {

    private ZahtevZaSertifikatRepository zahtevZaSertifikatRepository;

    @Autowired
    public ZahtevZaSertifikatController(ZahtevZaSertifikatRepository zahtevZaSertifikatRepository) {
        this.zahtevZaSertifikatRepository = zahtevZaSertifikatRepository;
    }

    @PostMapping
    public ResponseEntity<ZahtevZaSertifikat> write(@RequestBody ZahtevZaSertifikat zahtevZaSertifikat)
            throws Exception {
        var id = zahtevZaSertifikatRepository.save(zahtevZaSertifikat);
        System.out.println("Created zahtev za sertifikat with id : " + id);
        return new ResponseEntity<>(zahtevZaSertifikat, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<ZahtevZaSertifikat> read(@PathVariable UUID id) throws Exception {
        return new ResponseEntity<>(zahtevZaSertifikatRepository.read(id).get(), HttpStatus.OK);
    }
}
