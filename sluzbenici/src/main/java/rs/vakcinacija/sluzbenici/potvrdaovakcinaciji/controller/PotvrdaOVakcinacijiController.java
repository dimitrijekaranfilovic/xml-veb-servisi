package rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.dto.NaprednaPretragaRequest;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model.KolekcijaPotvrdaOVakcinaciji;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model.PotvrdaOVakcinaciji;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.service.PotvrdaOVakcinacijiService;

import java.util.UUID;

@RestController
@RequestMapping(value = "potvrda-o-vakcinaciji")
public class PotvrdaOVakcinacijiController {

    private final PotvrdaOVakcinacijiService potvrdaOVakcinacijiService;

    @Autowired
    public PotvrdaOVakcinacijiController(PotvrdaOVakcinacijiService potvrdaOVakcinacijiService) {
        this.potvrdaOVakcinacijiService = potvrdaOVakcinacijiService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PotvrdaOVakcinaciji create(@RequestBody PotvrdaOVakcinaciji potvrdaOVakcinaciji) throws Exception {
        var id = this.potvrdaOVakcinacijiService.create(potvrdaOVakcinaciji);
        System.out.println("Created potvrda o vakcinaciji with id: " + id);
        return potvrdaOVakcinaciji;
    }

    @GetMapping(value = "/html/{id}")
    public String generateHtml(@PathVariable UUID id) throws Exception {
        return this.potvrdaOVakcinacijiService.generateHTML(this.read(id), "src/main/resources/xslt/potvrda_o_vakcinaciji.xsl");
    }

    @GetMapping(value = "/pdf/{id}")
    public InputStreamResource pdf(@PathVariable UUID id) throws Exception {
        var pdf = this.potvrdaOVakcinacijiService.generatePDF(this.potvrdaOVakcinacijiService.read(id), "src/main/resources/xsl-fo/potvrda_o_vakcinaciji_fo.xsl");
        return new InputStreamResource(pdf);
    }

    @GetMapping(value = "/za-gradjanina")
    public KolekcijaPotvrdaOVakcinaciji readForCitizen(NaprednaPretragaRequest request) {
        return KolekcijaPotvrdaOVakcinaciji.of(potvrdaOVakcinacijiService.readForCitizen(request.getQuery(), request));
    }

    @GetMapping(value = "/{id}")
    public PotvrdaOVakcinaciji read(@PathVariable UUID id) throws Exception {
        return this.potvrdaOVakcinacijiService.read(id);
    }

    @GetMapping(value = "/string-pdf/{id}")
    public byte[] stringPdf(@PathVariable UUID id) throws Exception {
        var pdf = potvrdaOVakcinacijiService.generatePDF(potvrdaOVakcinacijiService.read(id),
                "src/main/resources/xsl-fo/potvrda_o_vakcinaciji_fo.xsl");
        return pdf.readAllBytes();
    }
}
