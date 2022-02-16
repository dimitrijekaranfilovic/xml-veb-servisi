package rs.vakcinacija.sluzbenici.saglasnost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.sluzbenici.saglasnost.model.KolekcijaSaglasnosti;
import rs.vakcinacija.sluzbenici.saglasnost.dto.NaprednaPretragaRequest;
import rs.vakcinacija.sluzbenici.saglasnost.service.SaglasnostClient;

@RestController
@RequestMapping(value = "saglasnost")
public class SaglasnostController {
    private final SaglasnostClient saglasnostClient;

    @Autowired
    public SaglasnostController(SaglasnostClient saglasnostClient) {
        this.saglasnostClient = saglasnostClient;
    }

    @GetMapping(value = "/za-gradjanina")
    public KolekcijaSaglasnosti readForCitizen(NaprednaPretragaRequest request) {
        return saglasnostClient.readForCitizen(request.getEmail(), request.getQuery());
    }
}
