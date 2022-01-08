package rs.vakcinacija.sluzbenici.digitalnisertifikat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.model.DigitalniSertifikat;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.repository.DigitalniSertifikatRepository;

import java.util.UUID;

@RestController
@RequestMapping(value = "digitalni-sertifikat")
public class DigitalniSertifikatController {

    private final DigitalniSertifikatRepository digitalniSertifikatRepository;

    @Autowired
    public DigitalniSertifikatController(DigitalniSertifikatRepository digitalniSertifikatRepository) {
        this.digitalniSertifikatRepository = digitalniSertifikatRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DigitalniSertifikat create(@RequestBody DigitalniSertifikat digitalniSertifikat) throws Exception {
        var id = digitalniSertifikatRepository.save(digitalniSertifikat);
        System.out.println("Created digitalni sertifikat with id: " + id);
        return digitalniSertifikat;
    }

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public DigitalniSertifikat read(@PathVariable UUID id) throws Exception {
        return digitalniSertifikatRepository.read(id).get();
    }

}
