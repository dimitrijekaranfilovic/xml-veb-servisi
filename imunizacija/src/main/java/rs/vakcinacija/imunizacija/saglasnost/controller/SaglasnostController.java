package rs.vakcinacija.imunizacija.saglasnost.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.imunizacija.saglasnost.dto.SaglasnostCreateRequest;
import rs.vakcinacija.imunizacija.saglasnost.model.KolekcijaSaglasnosti;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaglasnostCreateRequest create(@RequestBody SaglasnostCreateRequest saglasnost) throws Exception {
        var savedSaglasnost = saglasnostService.createFirstHalfOfDocument(saglasnostCreateRequestToSaglasnost.convert(saglasnost));
        return saglasnostToSaglasnostCreateRequest.convert(savedSaglasnost);
    }

    @GetMapping(value = "/{id}")
    public SaglasnostZaSprovodjenjeImunizacije read(@PathVariable UUID id) throws Exception {
        return saglasnostService.read(id);
    }

    @GetMapping(value = "/za-gradjanina")
    public KolekcijaSaglasnosti readForCitizen(@RequestParam(defaultValue = "") String query) throws Exception {
        return KolekcijaSaglasnosti.of(saglasnostService.read(query));
    }
}
