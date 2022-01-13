package rs.vakcinacija.sluzbenici.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.vakcinacija.zajednicko.email.service.EmailService;
import rs.vakcinacija.zajednicko.rabbitmq.MessageBroker;

@Configuration
public class EmailConfig {

    @Bean
    public EmailService emailService(MessageBroker messageBroker) {
        return new EmailService(messageBroker);
    }
}
