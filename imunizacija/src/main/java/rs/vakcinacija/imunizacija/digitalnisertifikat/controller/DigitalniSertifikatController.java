package rs.vakcinacija.imunizacija.digitalnisertifikat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.vakcinacija.imunizacija.digitalnisertifikat.model.KolekcijaDigitalnihSertifikata;
import rs.vakcinacija.imunizacija.digitalnisertifikat.service.DigitalniSertifikatService;

import java.io.ByteArrayInputStream;
import java.util.UUID;


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

    @GetMapping(value = "pdf/{id}")
    public ResponseEntity<InputStreamResource> readPDF(@PathVariable UUID id) throws Exception {
        return new ResponseEntity<>(new InputStreamResource(
                new ByteArrayInputStream(
                        digitalniSertifikatService.getPDFRepresentation(id))),
                HttpStatus.OK);
    }

    @GetMapping(value = "html/{id}")
    public String generateHtml(@PathVariable UUID id) throws Exception {
        return digitalniSertifikatService.getHTMLRepresentation(id);
    }

}
