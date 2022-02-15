package rs.vakcinacija.sluzbenici.saglasnost.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import rs.vakcinacija.sluzbenici.saglasnost.model.KolekcijaSaglasnosti;

@FeignClient(value = "saglasnostClient", url = "http://localhost:8081/saglasnost")
public interface SaglasnostClient {

    @GetMapping(value = "/za-gradjanina/{email}", produces = "application/xml")
    KolekcijaSaglasnosti readForCitizen(@PathVariable String email, @RequestParam String query);
}
