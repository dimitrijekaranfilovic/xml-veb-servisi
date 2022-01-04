package rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model.PotvrdaOVakcinaciji;

@RestController
@RequestMapping(value = "potvrda-o-vakcinaciji",
        produces = MediaType.APPLICATION_XML_VALUE,
        consumes = MediaType.APPLICATION_XML_VALUE)
public class PotvrdaOVakcinacijiController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PotvrdaOVakcinaciji test(@RequestBody PotvrdaOVakcinaciji potvrdaOVakcinaciji) {
        return potvrdaOVakcinaciji;
    }
}
