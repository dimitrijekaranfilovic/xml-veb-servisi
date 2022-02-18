package rs.vakcinacija.imunizacija.digitalnisertifikat.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import rs.vakcinacija.imunizacija.digitalnisertifikat.model.DigitalniSertifikat;
import rs.vakcinacija.imunizacija.digitalnisertifikat.model.KolekcijaDigitalnihSertifikata;

import java.util.UUID;


@FeignClient(value = "digitalniSertifikatClient", url = "http://localhost:8082/digitalni-sertifikat")
public interface DigitalniSertifikatClient {

    @GetMapping(value = "/za-gradjanina")
    KolekcijaDigitalnihSertifikata read(@RequestParam String email, @RequestParam String query);

    @GetMapping(value = "/pdf/{id}")
    InputStreamResource pdf(@PathVariable UUID id) throws Exception;

    @GetMapping(value = "/html/{id}")
    String html(@PathVariable UUID id) throws Exception;
}
