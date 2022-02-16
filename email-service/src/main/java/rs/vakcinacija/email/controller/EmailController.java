package rs.vakcinacija.email.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.vakcinacija.email.model.SendEmailRequest;
import rs.vakcinacija.email.service.EmailService;

@RestController
@RequestMapping("/email")
@Slf4j
public class EmailController {
    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public void sendEmail(@RequestBody SendEmailRequest request) {
        log.info(String.format("Received message from email queue: %s", request));
        emailService.sendEmail(request);
    }
}
