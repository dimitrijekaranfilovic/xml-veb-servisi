package rs.vakcinacija.imunizacija.saglasnost.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.vakcinacija.imunizacija.saglasnost.model.SaglasnostZaSprovodjenjeImunizacije;

@RestController
@RequestMapping(value = "saglasnost",
        produces = MediaType.APPLICATION_XML_VALUE,
        consumes = MediaType.APPLICATION_XML_VALUE)
public class SaglasnostController {

    @PostMapping
    public ResponseEntity<SaglasnostZaSprovodjenjeImunizacije> test(@RequestBody SaglasnostZaSprovodjenjeImunizacije saglasnost) {
        return new ResponseEntity<>(saglasnost, HttpStatus.CREATED);
    }
}
