package rs.vakcinacija.imunizacija.saglasnost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.imunizacija.saglasnost.model.SaglasnostZaSprovodjenjeImunizacije;
import rs.vakcinacija.imunizacija.saglasnost.repository.SaglasnostRepository;

import java.util.UUID;

@RestController
@RequestMapping(value = "saglasnost")
public class SaglasnostController {

    private final SaglasnostRepository saglasnostRepository;

    @Autowired
    public SaglasnostController(SaglasnostRepository saglasnostRepository) {
        this.saglasnostRepository = saglasnostRepository;
    }

    @PostMapping
    public ResponseEntity<SaglasnostZaSprovodjenjeImunizacije> testWrite(@RequestBody SaglasnostZaSprovodjenjeImunizacije saglasnost) throws Exception {
        var id = saglasnostRepository.save(saglasnost);
        System.out.println("Created entity with id: " + id);
        return new ResponseEntity<>(saglasnost, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<SaglasnostZaSprovodjenjeImunizacije> testRead(@PathVariable UUID id) throws Exception {
        return new ResponseEntity<>(saglasnostRepository.read(id).get(), HttpStatus.CREATED);
    }
}
