package rs.vakcinacija.email.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import rs.vakcinacija.email.model.SendEmailRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(SendEmailRequest sendEmailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(sendEmailRequest.getTo());
        message.setSubject(sendEmailRequest.getSubject());
        message.setText(sendEmailRequest.getContent());
        mailSender.send(message);
    }

}
