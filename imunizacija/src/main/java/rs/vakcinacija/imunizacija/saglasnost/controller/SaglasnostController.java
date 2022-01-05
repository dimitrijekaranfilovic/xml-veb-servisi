package rs.vakcinacija.imunizacija.saglasnost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.imunizacija.saglasnost.model.SaglasnostZaSprovodjenjeImunizacije;
import rs.vakcinacija.imunizacija.saglasnost.repository.SaglasnostRepository;

@RestController
@RequestMapping(value = "saglasnost",
        produces = MediaType.APPLICATION_XML_VALUE,
        consumes = MediaType.APPLICATION_XML_VALUE)
public class SaglasnostController {

    @Autowired
    SaglasnostRepository saglasnostRepository;

    @PostMapping
    public ResponseEntity<SaglasnostZaSprovodjenjeImunizacije> testWrite(@RequestBody SaglasnostZaSprovodjenjeImunizacije saglasnost) throws Exception {
        saglasnostRepository.save(saglasnost);
        return new ResponseEntity<>(saglasnost, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<SaglasnostZaSprovodjenjeImunizacije> testRead(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(saglasnostRepository.retrieve(), HttpStatus.CREATED);
    }
}
