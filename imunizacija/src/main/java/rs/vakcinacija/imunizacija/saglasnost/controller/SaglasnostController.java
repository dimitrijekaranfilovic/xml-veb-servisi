package rs.vakcinacija.imunizacija.saglasnost.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.imunizacija.saglasnost.dto.SaglasnostCreateRequest;
import rs.vakcinacija.imunizacija.saglasnost.model.SaglasnostZaSprovodjenjeImunizacije;
import rs.vakcinacija.imunizacija.saglasnost.service.SaglasnostService;
import rs.vakcinacija.imunizacija.saglasnost.support.SaglasnostCreateRequestToSaglasnost;
import rs.vakcinacija.imunizacija.saglasnost.support.SaglasnostToSaglasnostCreateRequest;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "saglasnost")
public class SaglasnostController {

    private final SaglasnostService saglasnostService;
    private final SaglasnostToSaglasnostCreateRequest saglasnostToSaglasnostCreateRequest;
    private final SaglasnostCreateRequestToSaglasnost saglasnostCreateRequestToSaglasnost;

    @Autowired
    public SaglasnostController(SaglasnostService saglasnostService,
                                SaglasnostToSaglasnostCreateRequest saglasnostToSaglasnostCreateRequest,
                                SaglasnostCreateRequestToSaglasnost saglasnostCreateRequestToSaglasnost) {
        this.saglasnostService = saglasnostService;
        this.saglasnostToSaglasnostCreateRequest = saglasnostToSaglasnostCreateRequest;
        this.saglasnostCreateRequestToSaglasnost = saglasnostCreateRequestToSaglasnost;
    }

    @PostMapping("/test")
    public ResponseEntity<SaglasnostZaSprovodjenjeImunizacije> testWrite(@RequestBody SaglasnostZaSprovodjenjeImunizacije saglasnost) throws Exception {
        var savedSaglasnost = saglasnostService.create(saglasnost);
        return new ResponseEntity<>(savedSaglasnost, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<SaglasnostCreateRequest> create(@RequestBody SaglasnostCreateRequest saglasnost) throws Exception {
        var savedSaglasnost = saglasnostService.createFirstHalfOfDocument(saglasnostCreateRequestToSaglasnost.convert(saglasnost));
        return new ResponseEntity<>(saglasnostToSaglasnostCreateRequest.convert(savedSaglasnost), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<SaglasnostZaSprovodjenjeImunizacije> testRead(@PathVariable UUID id) throws Exception {
        return new ResponseEntity<>(saglasnostService.read(id), HttpStatus.OK);
    }
}
