package rs.vakcinacija.sluzbenici.saglasnost.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.sluzbenici.saglasnost.dto.KontraindikacijaDTO;
import rs.vakcinacija.sluzbenici.saglasnost.dto.PodaciOLekaruUstanoviDTO;
import rs.vakcinacija.sluzbenici.saglasnost.dto.VakcinaDTO;
import rs.vakcinacija.sluzbenici.saglasnost.model.KolekcijaSaglasnosti;
import rs.vakcinacija.sluzbenici.saglasnost.model.SaglasnostZaSprovodjenjeImunizacije;

import java.util.UUID;

@FeignClient(value = "saglasnostClient", url = "http://localhost:8081/saglasnost")
public interface SaglasnostClient {

    @GetMapping(value = "/za-gradjanina", produces = "application/xml")
    KolekcijaSaglasnosti readForCitizen(
            @RequestParam("email") String email,
            @RequestParam("query") String query
    );

    @GetMapping(value = "/za-sluzbenika", produces = "application/xml")
    KolekcijaSaglasnosti readFilteredByEmail(@RequestParam("email") String email);

    @GetMapping(value = "/{id}", produces = "application/xml")
    SaglasnostZaSprovodjenjeImunizacije read(@PathVariable UUID id);

    @PostMapping(value = "/{id}/podaci-o-lekaru-ustanovi")
    PodaciOLekaruUstanoviDTO createDoctorBuilding(@RequestBody PodaciOLekaruUstanoviDTO podaciOLekaruUstanoviDTO, @PathVariable UUID id);

    @GetMapping(value = "/{id}/odluka-komisije")
    void changeCommissionDecision(@PathVariable UUID id);

    @PostMapping(value = "/{id}/kontraindikacije")
    KontraindikacijaDTO addSideEffect(@PathVariable UUID id, @RequestBody KontraindikacijaDTO kontraindikacijaDTO);

    @PostMapping(value = "/{id}/vakcine")
    VakcinaDTO addVaccine(@PathVariable UUID id, @RequestBody VakcinaDTO vakcinaDTO);
}
