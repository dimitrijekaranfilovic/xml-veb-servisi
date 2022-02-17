package rs.vakcinacija.imunizacija.potvrdaovakcinaciji.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rs.vakcinacija.imunizacija.potvrdaovakcinaciji.model.KolekcijaPotvrdaOVakcinaciji;


@FeignClient(value = "potvrdaOVakcinacijiClient", url = "http://localhost:8082/potvrda-o-vakcinaciji")
public interface PotvrdaOVakcinacijiClient {

    @GetMapping(value = "/za-gradjanina")
    KolekcijaPotvrdaOVakcinaciji readForCitizen(@RequestParam String email, @RequestParam String query);
}
