package rs.vakcinacija.sluzbenici.vakcinacionipunkt.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.dto.NovaVakcinaDTO;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.dto.TerminDTO;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.model.DostupnaVakcina;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.model.VakcinacioniPunkt;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.service.VakcinacioniPunktService;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/vakcinacioni-punkt")
public class VakcinacioniPunktController {

    private final VakcinacioniPunktService vakcinacioniPunktService;

    @Autowired
    public VakcinacioniPunktController(VakcinacioniPunktService vakcinacioniPunktService) {
        this.vakcinacioniPunktService = vakcinacioniPunktService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createPunkt(@RequestBody  VakcinacioniPunkt vakcinacioniPunkt) throws Exception {
        return this.vakcinacioniPunktService.create(vakcinacioniPunkt);
    }


    @GetMapping(value = "/{id}")
    public VakcinacioniPunkt get(@PathVariable UUID id) throws Exception {
        return this.vakcinacioniPunktService.read(id);
    }

    @PostMapping(value = "/termin/{id}")
    public VakcinacioniPunkt createTermin(@PathVariable UUID id, @RequestBody TerminDTO terminDTO) throws Exception {
        return this.vakcinacioniPunktService.createTermin(id, terminDTO.getDatumVreme());
    }

    @PostMapping(value = "/dostupna-vakcina/{id}")
    public VakcinacioniPunkt createDostupnaVakcina(@PathVariable UUID id, @RequestBody NovaVakcinaDTO novaVakcinaDTO) throws Exception{
        return this.vakcinacioniPunktService.createDostupnaVakcina(id, novaVakcinaDTO.getNazivVakcine(), novaVakcinaDTO.getStanjeVakcine());
    }

    @PutMapping("/dostupna-vakcina/{id}")
    public VakcinacioniPunkt updateDostupnaVakcina(@PathVariable UUID id, @RequestBody NovaVakcinaDTO novaVakcinaDTO) throws Exception{
        return this.vakcinacioniPunktService.updateDostupnaVakcina(id, novaVakcinaDTO.getNazivVakcine(), novaVakcinaDTO.getStanjeVakcine());
    }

}
