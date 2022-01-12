package rs.vakcinacija.imunizacija.saglasnost.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.imunizacija.saglasnost.model.SaglasnostZaSprovodjenjeImunizacije;
import rs.vakcinacija.imunizacija.saglasnost.service.SaglasnostService;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "saglasnost")
public class SaglasnostController {

    private final SaglasnostService saglasnostService;

    public SaglasnostController(SaglasnostService saglasnostService) {
        this.saglasnostService = saglasnostService;
    }


    @PostMapping
    public ResponseEntity<SaglasnostZaSprovodjenjeImunizacije> testWrite(@RequestBody SaglasnostZaSprovodjenjeImunizacije saglasnost) throws Exception {
        var savedSaglasnost = saglasnostService.create(saglasnost);
        return new ResponseEntity<>(savedSaglasnost, HttpStatus.CREATED);
    }

//    @PostMapping(value = "raw")
//    public ResponseEntity<SaglasnostZaSprovodjenjeImunizacije> testRawWrite(@RequestBody SaglasnostZaSprovodjenjeImunizacije saglasnost) throws Exception {
//        var updatedSaglasnost = saglasnostService.insertRDFAttributes(saglasnost);
//        log.info(updatedSaglasnost.getDatum().getProperty());
//        var savedSaglasnost = saglasnostService.create(updatedSaglasnost);
//        return new ResponseEntity<>(savedSaglasnost, HttpStatus.CREATED);
//    }

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<SaglasnostZaSprovodjenjeImunizacije> testRead(@PathVariable UUID id) throws Exception {
        return new ResponseEntity<>(saglasnostService.read(id), HttpStatus.CREATED);
    }
}
