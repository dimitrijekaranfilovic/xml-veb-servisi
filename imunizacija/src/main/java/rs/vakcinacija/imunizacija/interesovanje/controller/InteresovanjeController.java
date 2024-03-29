package rs.vakcinacija.imunizacija.interesovanje.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.imunizacija.interesovanje.model.Interesovanje;
import rs.vakcinacija.imunizacija.interesovanje.model.KolekcijaInteresovanja;
import rs.vakcinacija.imunizacija.interesovanje.service.InteresovanjeService;

import java.util.Date;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "interesovanje")
public class InteresovanjeController {

    private final InteresovanjeService interesovanjeService;

    @Autowired
    public InteresovanjeController(InteresovanjeService interesovanjeService) {
        this.interesovanjeService = interesovanjeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Interesovanje write(@RequestBody Interesovanje interesovanje) throws Exception {
        return interesovanjeService.submitInteresovanjeAndSendEmail(interesovanje);
    }

    @GetMapping
    public KolekcijaInteresovanja readAll() throws Exception {
        log.info("EO ME");
        return KolekcijaInteresovanja.of(this.interesovanjeService.read());
    }

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public Interesovanje read(@PathVariable UUID id) throws Exception {
        return interesovanjeService.read(id);
    }

    @GetMapping(value = "/all/{email}")
    public KolekcijaInteresovanja getAllForUser(@PathVariable String email) throws Exception {
        return KolekcijaInteresovanja.of(interesovanjeService.getAllForUser(email));
    }

    @GetMapping(value = "/html/{id}")
    public String generateHtml(@PathVariable UUID id) throws Exception {
        return interesovanjeService.getHTMLRepresentation(id);
    }

    @GetMapping(value = "pdf/{id}")
    public ResponseEntity<InputStreamResource> readPDF(@PathVariable UUID id) throws Exception {
        return new ResponseEntity<>(new InputStreamResource(interesovanjeService.getPDFRepresentation(id)), HttpStatus.OK);
    }

    @PostMapping(value = "/datum-termina/{id}")
    public void setDatumTermina(@PathVariable UUID id, @RequestBody long datumTermina) throws Exception {
        this.interesovanjeService.setDatumTermina(id, new Date(datumTermina));
    }

}
