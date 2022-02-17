package rs.vakcinacija.imunizacija.digitalnisertifikat.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rs.vakcinacija.imunizacija.digitalnisertifikat.model.KolekcijaDigitalnihSertifikata;


@FeignClient(value = "digitalniSertifikatClient", url = "http://localhost:8082/digitalni-sertifikat")
public interface DigitalniSertifikatClient {

    @GetMapping(value = "/za-gradjanina")
    KolekcijaDigitalnihSertifikata read(@RequestParam String email, @RequestParam String query);
}
