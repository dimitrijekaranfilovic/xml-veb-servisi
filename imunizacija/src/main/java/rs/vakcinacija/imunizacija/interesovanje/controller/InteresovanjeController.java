package rs.vakcinacija.imunizacija.interesovanje.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.imunizacija.interesovanje.model.Interesovanje;
import rs.vakcinacija.imunizacija.interesovanje.model.KolekcijaInteresovanja;
import rs.vakcinacija.imunizacija.interesovanje.service.InteresovanjeService;
import rs.vakcinacija.zajednicko.model.RDFDate;

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
    public Interesovanje write(@RequestBody Interesovanje interesovanje) throws Exception {
        interesovanje.setDatum(RDFDate.of(new Date()));
        return interesovanjeService.create(interesovanje);
    }

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public Interesovanje read(@PathVariable UUID id) throws Exception {
        return interesovanjeService.read(id);
    }

    @GetMapping(value = "/all/{email}")
    public KolekcijaInteresovanja getAllForUser(@PathVariable String email) throws Exception {
        return KolekcijaInteresovanja.of(interesovanjeService.getAllForUser(email));
    }

}
