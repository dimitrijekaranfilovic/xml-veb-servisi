package rs.vakcinacija.sluzbenici.digitalnisertifikat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.model.DigitalniSertifikat;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.service.DigitalniSertifikatService;

import java.util.UUID;

@RestController
@RequestMapping(value = "digitalni-sertifikat")
public class DigitalniSertifikatController {

    private final DigitalniSertifikatService digitalniSertifikatService;

    @Autowired
    public DigitalniSertifikatController(DigitalniSertifikatService digitalniSertifikatService) {
        this.digitalniSertifikatService = digitalniSertifikatService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DigitalniSertifikat create(@RequestBody DigitalniSertifikat digitalniSertifikat) throws Exception {
        return digitalniSertifikatService.create(digitalniSertifikat);
    }

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public DigitalniSertifikat read(@PathVariable UUID id) throws Exception {
        return digitalniSertifikatService.read(id);
    }

}
