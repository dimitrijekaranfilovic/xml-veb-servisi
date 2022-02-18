package rs.vakcinacija.imunizacija.potvrdaovakcinaciji.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import rs.vakcinacija.imunizacija.potvrdaovakcinaciji.model.KolekcijaPotvrdaOVakcinaciji;

import java.util.UUID;


@FeignClient(value = "potvrdaOVakcinacijiClient", url = "http://localhost:8082/potvrda-o-vakcinaciji")
public interface PotvrdaOVakcinacijiClient {

    @GetMapping(value = "/za-gradjanina")
    KolekcijaPotvrdaOVakcinaciji readForCitizen(@RequestParam String email, @RequestParam String query);

    @GetMapping(value = "/pdf/{id}")
    InputStreamResource pdf(@PathVariable UUID id) throws Exception;

    @GetMapping(value = "/html/{id}")
    String html(@PathVariable UUID id) throws Exception;
}
