package rs.vakcinacija.sluzbenici.digitalnisertifikat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.model.DigitalniSertifikat;

@RestController
@RequestMapping(value = "digitalni-sertifikat",
        produces = MediaType.APPLICATION_XML_VALUE,
        consumes = MediaType.APPLICATION_XML_VALUE)
public class DigitalniSertifikatController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DigitalniSertifikat test(@RequestBody DigitalniSertifikat digitalniSertifikat) {
        return digitalniSertifikat;
    }

}
