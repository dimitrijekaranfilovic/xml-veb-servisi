package rs.vakcinacija.sluzbenici.digitalnisertifikat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.model.DigitalniSertifikat;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.model.KolekcijaDigitalnihSertifikata;
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

    @GetMapping
    public KolekcijaDigitalnihSertifikata read() throws Exception {
        return KolekcijaDigitalnihSertifikata.of(digitalniSertifikatService.read());
    }

    @GetMapping(value = "/za-gradjanina/{email}")
    public KolekcijaDigitalnihSertifikata readForCitizen(@PathVariable String email, @RequestParam(defaultValue = "") String query) throws Exception {
        return KolekcijaDigitalnihSertifikata.of(digitalniSertifikatService.readForCitizen(email, query));
    }

    @GetMapping(value = "/{id}")
    public DigitalniSertifikat read(@PathVariable UUID id) throws Exception {
        return digitalniSertifikatService.read(id);
    }

}
