package rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model.PotvrdaOVakcinaciji;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.repository.PotvrdaOVakcinacijiRepository;

import java.util.UUID;

@RestController
@RequestMapping(value = "potvrda-o-vakcinaciji")
public class PotvrdaOVakcinacijiController {

    private final PotvrdaOVakcinacijiRepository potvrdaOVakcinacijiRepository;

    @Autowired
    public PotvrdaOVakcinacijiController(PotvrdaOVakcinacijiRepository potvrdaOVakcinacijiRepository) {
        this.potvrdaOVakcinacijiRepository = potvrdaOVakcinacijiRepository;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PotvrdaOVakcinaciji create(@RequestBody PotvrdaOVakcinaciji potvrdaOVakcinaciji) throws Exception {
        var id = potvrdaOVakcinacijiRepository.save(potvrdaOVakcinaciji);
        System.out.println("Created potvrda o vakcinaciji with id: " + id);
        return potvrdaOVakcinaciji;
    }

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public PotvrdaOVakcinaciji read(@PathVariable UUID id) throws Exception {
        return potvrdaOVakcinacijiRepository.read(id).get();
    }
}
