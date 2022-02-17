package rs.vakcinacija.sluzbenici.saglasnost.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.sluzbenici.saglasnost.dto.KontraindikacijaDTO;
import rs.vakcinacija.sluzbenici.saglasnost.dto.PodaciOLekaruUstanoviDTO;
import rs.vakcinacija.sluzbenici.saglasnost.dto.VakcinaDTO;
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

    @PostMapping(value = "/{id}/podaci-o-lekaru-ustanovi")
    public PodaciOLekaruUstanoviDTO createDoctorBuilding(@RequestBody PodaciOLekaruUstanoviDTO podaciOLekaruUstanoviDTO, @PathVariable UUID id) {
        return saglasnostClient.createDoctorBuilding(podaciOLekaruUstanoviDTO, id);
    }

    @GetMapping(value = "/{id}/odluka-komisije")
    public void changeCommissionDecision(@PathVariable UUID id){
        saglasnostClient.changeCommissionDecision(id);
    }

    @PostMapping(value = "/{id}/kontraindikacije")
    public KontraindikacijaDTO addSideEffect(@PathVariable UUID id, @RequestBody KontraindikacijaDTO kontraindikacijaDTO) {
        saglasnostClient.addSideEffect(id, kontraindikacijaDTO);
        return kontraindikacijaDTO;
    }

    @PostMapping(value = "/{id}/vakcine")
    public VakcinaDTO addVaccine(@PathVariable UUID id, @RequestBody VakcinaDTO vakcinaDTO) throws Exception {
        saglasnostClient.addVaccine(id, vakcinaDTO);
        //TODO: kreiraj potvrdu o vakcinaciji
        return vakcinaDTO;
    }
}
