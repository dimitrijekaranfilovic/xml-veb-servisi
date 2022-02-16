package rs.vakcinacija.sluzbenici.saglasnost.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.sluzbenici.saglasnost.model.KolekcijaSaglasnosti;
import rs.vakcinacija.sluzbenici.saglasnost.model.Saglasnost;
import rs.vakcinacija.sluzbenici.saglasnost.model.SaglasnostZaSprovodjenjeImunizacije;
import rs.vakcinacija.sluzbenici.saglasnost.service.SaglasnostClient;

import java.util.UUID;

@RestController
@RequestMapping(value = "saglasnost")
@Slf4j
public class SaglasnostController {
    private final SaglasnostClient saglasnostClient;

    @Autowired
    public SaglasnostController(SaglasnostClient saglasnostClient) {
        this.saglasnostClient = saglasnostClient;
    }

    @GetMapping(value = "/za-gradjanina/{email}")
    public KolekcijaSaglasnosti readForCitizen(@PathVariable String email, @RequestParam(defaultValue = "") String query) {
        return saglasnostClient.readForCitizen(email, query);
    }

    @GetMapping(value = "/za-sluzbenika")
    public KolekcijaSaglasnosti readForBureaucrat(@RequestParam(defaultValue = "") String email){
        return saglasnostClient.readFilteredByEmail(email);
    }

    @GetMapping(value = "/jedan-za-sluzbenika/{id}")
    public SaglasnostZaSprovodjenjeImunizacije readOne(@PathVariable UUID id) {
        return saglasnostClient.read(id);
    }
}
