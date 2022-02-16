package rs.vakcinacija.imunizacija.interesovanje.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rs.vakcinacija.imunizacija.interesovanje.event.InteresovanjePodnetoEvent;

@FeignClient(value = "vakcinacioniPunkClient", url = "http://localhost:8082/vakcinacioni-punkt")
public interface VakcinacioniPunktClient {

    @PostMapping(value = "/podneto-interesoavnje")
    void onInteresetSubmitted(@RequestBody InteresovanjePodnetoEvent interesovanjePodnetoEvent);
}
