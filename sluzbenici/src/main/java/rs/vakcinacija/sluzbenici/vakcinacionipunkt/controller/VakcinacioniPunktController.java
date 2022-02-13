package rs.vakcinacija.sluzbenici.vakcinacionipunkt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.model.VakcinacioniPunkt;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.service.VakcinacioniPunktService;

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
}
