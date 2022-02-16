package rs.vakcinacija.sluzbenici.vakcinacionipunkt.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.dto.NovaVakcinaDTO;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.dto.TerminDTO;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.event.InteresovanjePodnetoEvent;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.model.KolekcijaVakcinacionihPunktova;
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
    public VakcinacioniPunkt createPunkt(@RequestBody VakcinacioniPunkt vakcinacioniPunkt) throws Exception {
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
    public VakcinacioniPunkt createDostupnaVakcina(@PathVariable UUID id, @RequestBody NovaVakcinaDTO novaVakcinaDTO) throws Exception {
        return this.vakcinacioniPunktService.createDostupnaVakcina(id, novaVakcinaDTO.getNazivVakcine(), novaVakcinaDTO.getStanjeVakcine());
    }

    @PutMapping("/dostupna-vakcina/{id}")
    public VakcinacioniPunkt updateDostupnaVakcina(@PathVariable UUID id, @RequestBody NovaVakcinaDTO novaVakcinaDTO) throws Exception {
        return this.vakcinacioniPunktService.updateDostupnaVakcina(id, novaVakcinaDTO.getNazivVakcine(), novaVakcinaDTO.getStanjeVakcine());
    }

    @GetMapping
    public KolekcijaVakcinacionihPunktova getPunktovi() throws Exception {
        return KolekcijaVakcinacionihPunktova.of(this.vakcinacioniPunktService.read());
    }

    @RabbitListener(queues = "InteresovanjePodnetoEvent")
    public void onRequestApproved(InteresovanjePodnetoEvent event) throws Exception {
        log.info(String.format("Podneto interesovanje: '%s' '%s'", event.getMesto(), event.getEmail()));
        this.vakcinacioniPunktService.assignAppointment(event.getMesto(), event.getOdabraneVakcine(),
                event.getEmail(), event.getInteresovanjeId());
    }


}
