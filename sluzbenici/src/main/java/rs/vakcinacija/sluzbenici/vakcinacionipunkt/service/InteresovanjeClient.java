package rs.vakcinacija.sluzbenici.vakcinacionipunkt.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(value = "interesovanjeClient", url = "http://localhost:8081/interesovanje")
public interface InteresovanjeClient {

    @PostMapping(value = "/datum-termina/{id}")
    void setDatumTermina(@PathVariable UUID id, @RequestBody long datumTermina) throws Exception;
}
