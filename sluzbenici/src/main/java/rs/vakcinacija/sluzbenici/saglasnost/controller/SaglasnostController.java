package rs.vakcinacija.sluzbenici.saglasnost.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model.PotvrdaOVakcinaciji;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.service.PotvrdaOVakcinacijiService;
import rs.vakcinacija.sluzbenici.saglasnost.dto.KontraindikacijaDTO;
import rs.vakcinacija.sluzbenici.saglasnost.dto.NaprednaPretragaRequest;
import rs.vakcinacija.sluzbenici.saglasnost.dto.PodaciOLekaruUstanoviDTO;
import rs.vakcinacija.sluzbenici.saglasnost.dto.VakcinaDTO;
import rs.vakcinacija.sluzbenici.saglasnost.model.KolekcijaSaglasnosti;
import rs.vakcinacija.sluzbenici.saglasnost.model.SaglasnostZaSprovodjenjeImunizacije;
import rs.vakcinacija.sluzbenici.saglasnost.service.SaglasnostClient;

import java.io.ByteArrayInputStream;
import java.util.UUID;

@RestController
@RequestMapping(value = "saglasnost")
@Slf4j
public class SaglasnostController {
    private final SaglasnostClient saglasnostClient;
    private final PotvrdaOVakcinacijiService potvrdaOVakcinacijiService;

    @Autowired
    public SaglasnostController(SaglasnostClient saglasnostClient, PotvrdaOVakcinacijiService potvrdaOVakcinacijiService) {
        this.saglasnostClient = saglasnostClient;
        this.potvrdaOVakcinacijiService = potvrdaOVakcinacijiService;
    }

    @GetMapping(value = "/za-gradjanina")
    public KolekcijaSaglasnosti readForCitizen(NaprednaPretragaRequest request) {
        return saglasnostClient.readForCitizen(request.getEmail(), request.getQuery(), request.getVaccine(),
                request.getPlace(), request.getVaccinationPlace(), request.getIssueDate());
    }

    @GetMapping(value = "/html/{id}")
    public String html(@PathVariable UUID id) {
        return saglasnostClient.html(id);
    }

    @GetMapping(value = "/pdf/{id}")
    public InputStreamResource pdf(@PathVariable UUID id) {
        return new InputStreamResource(
                new ByteArrayInputStream(
                        saglasnostClient.pdf(id)
                )
        );
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
    public PotvrdaOVakcinaciji addVaccine(@PathVariable UUID id, @RequestBody VakcinaDTO vakcinaDTO) throws Exception {
        var saglasnost = saglasnostClient.addVaccine(id, vakcinaDTO);
        return this.potvrdaOVakcinacijiService.issueConfirmation(saglasnost);
    }
}
