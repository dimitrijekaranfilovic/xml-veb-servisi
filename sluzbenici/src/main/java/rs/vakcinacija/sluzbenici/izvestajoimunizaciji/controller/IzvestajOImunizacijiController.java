package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.dto.ZahtevZaKreiranjeIzvestajaDTO;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model.IzvestajOImunizaciji;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model.KolekcijaIzvestajaOImunizaciji;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.service.IzvestajOImunizacijiService;

import java.util.UUID;

@RestController
@RequestMapping(value = "izvestaj-o-imunizaciji")
public class IzvestajOImunizacijiController {


    private final IzvestajOImunizacijiService izvestajOImunizacijiService;

    @Autowired
    public IzvestajOImunizacijiController(IzvestajOImunizacijiService izvestajOImunizacijiService) {
        this.izvestajOImunizacijiService = izvestajOImunizacijiService;
    }

    @GetMapping(value = "/pdf/{id}")
    public InputStreamResource pdf(@PathVariable UUID id) throws Exception {
        var pdf = this.izvestajOImunizacijiService.generatePDF(this.izvestajOImunizacijiService.read(id), "src/main/resources/xsl-fo/izvestaj_o_imunizaciji_fo.xsl");
        return new InputStreamResource(pdf);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IzvestajOImunizaciji create(@RequestBody ZahtevZaKreiranjeIzvestajaDTO zahtevZaKreiranjeIzvestajaDTO) throws Exception {
       return this.izvestajOImunizacijiService.generateIzvestaj(zahtevZaKreiranjeIzvestajaDTO.getPeriodOd(), zahtevZaKreiranjeIzvestajaDTO.getPeriodDo());
    }

    @GetMapping(value = "/html/{id}")
    public String generateHtml(@PathVariable UUID id) throws Exception {
        return this.izvestajOImunizacijiService.generateHTML(this.izvestajOImunizacijiService.read(id), "src/main/resources/xslt/izvestaj_o_imunizaciji.xsl");
    }

    @PostMapping(value = "/potpun")
    @ResponseStatus(HttpStatus.CREATED)
    public IzvestajOImunizaciji createFull(@RequestBody IzvestajOImunizaciji izvestajOImunizaciji) throws Exception {
        return this.izvestajOImunizacijiService.create(izvestajOImunizaciji);
    }

    @GetMapping
    public KolekcijaIzvestajaOImunizaciji read() throws Exception {
        return KolekcijaIzvestajaOImunizaciji.of(this.izvestajOImunizacijiService.read());
    }

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public IzvestajOImunizaciji read(@PathVariable UUID id) throws Exception {
        return this.izvestajOImunizacijiService.read(id);
    }


}
