package rs.vakcinacija.sluzbenici.digitalnisertifikat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.dto.NaprednaPretragaRequest;
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

    @GetMapping(value = "/za-gradjanina")
    public KolekcijaDigitalnihSertifikata read(NaprednaPretragaRequest request) {
        return KolekcijaDigitalnihSertifikata.of(digitalniSertifikatService.readForCitizen(request.getQuery(), request));
    }

    @GetMapping(value = "/html/{id}")
    public String html(@PathVariable UUID id) throws Exception {
        return digitalniSertifikatService.generateHTML(digitalniSertifikatService.read(id), "src/main/resources/xslt/digitalni_sertifikat.xsl");
    }

    @GetMapping(value = "/pdf/{id}")
    public InputStreamResource pdf(@PathVariable UUID id) throws Exception {
        var pdf = digitalniSertifikatService.generatePDF(digitalniSertifikatService.read(id), "src/main/resources/xsl-fo/digitalni_sertifikat_fo.xsl");
        return new InputStreamResource(pdf);
    }

    @GetMapping(value = "/string-pdf/{id}")
    public byte[] stringPdf(@PathVariable UUID id) throws Exception {
        var pdf = digitalniSertifikatService.generatePDF(digitalniSertifikatService.read(id), "src/main/resources/xsl-fo/digitalni_sertifikat_fo.xsl");
        return pdf.readAllBytes();
    }

    @GetMapping(value = "/{id}")
    public DigitalniSertifikat read(@PathVariable UUID id) throws Exception {
        return digitalniSertifikatService.read(id);
    }

}
