package rs.vakcinacija.imunizacija.digitalnisertifikat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.vakcinacija.imunizacija.digitalnisertifikat.model.KolekcijaDigitalnihSertifikata;
import rs.vakcinacija.imunizacija.digitalnisertifikat.service.DigitalniSertifikatService;


@RestController
@RequestMapping(value = "digitalni-sertifikat")
public class DigitalniSertifikatController {

    private final DigitalniSertifikatService digitalniSertifikatService;

    @Autowired
    public DigitalniSertifikatController(DigitalniSertifikatService digitalniSertifikatService) {
        this.digitalniSertifikatService = digitalniSertifikatService;
    }

    @GetMapping(value = "/za-gradjanina/{email}")
    public KolekcijaDigitalnihSertifikata read(@PathVariable String email) {
        return digitalniSertifikatService.readForClient(email);
    }

}
