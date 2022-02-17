package rs.vakcinacija.imunizacija.potvrdaovakcinaciji.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.vakcinacija.imunizacija.potvrdaovakcinaciji.model.KolekcijaPotvrdaOVakcinaciji;
import rs.vakcinacija.imunizacija.potvrdaovakcinaciji.service.PotvrdaOVakcinacijiService;

@RestController
@RequestMapping(value = "potvrda-o-vakcinaciji")
public class PotvrdaOVakcinacijiController {

    private final PotvrdaOVakcinacijiService potvrdaOVakcinacijiService;

    @Autowired
    public PotvrdaOVakcinacijiController(PotvrdaOVakcinacijiService potvrdaOVakcinacijiService) {
        this.potvrdaOVakcinacijiService = potvrdaOVakcinacijiService;
    }

    @GetMapping(value = "/za-gradjanina/{email}")
    public KolekcijaPotvrdaOVakcinaciji read(@PathVariable String email) {
        return potvrdaOVakcinacijiService.readForClient(email);
    }
}
