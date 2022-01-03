package rs.vakcinacija.imunizacija.zahtevzasertifikat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.vakcinacija.imunizacija.zahtevzasertifikat.model.ZahtevZaSertifikat;

@RestController
@RequestMapping(value = "zahtev-za-sertifikat",
        produces = MediaType.APPLICATION_XML_VALUE,
        consumes = MediaType.APPLICATION_XML_VALUE)
public class ZahtevZaSertifikatController {

    @PostMapping
    public ResponseEntity<ZahtevZaSertifikat> get(@RequestBody ZahtevZaSertifikat zahtevZaSertifikat) {
        return new ResponseEntity<>(zahtevZaSertifikat, HttpStatus.CREATED);
    }
}
