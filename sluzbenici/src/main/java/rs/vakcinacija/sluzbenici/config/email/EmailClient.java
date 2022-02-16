package rs.vakcinacija.sluzbenici.config.email;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rs.vakcinacija.zajednicko.email.model.SendEmailRequest;

@FeignClient(value = "emailClient", url = "http://localhost:8088")
public interface EmailClient {

    @PostMapping(value = "email", consumes = "application/xml")
    void sendEmail(@RequestBody SendEmailRequest sendEmailRequest);
}
