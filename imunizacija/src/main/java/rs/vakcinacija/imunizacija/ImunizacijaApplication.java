package rs.vakcinacija.imunizacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import rs.vakcinacija.zajednicko.email.model.SendEmailRequest;
import rs.vakcinacija.zajednicko.email.service.EmailService;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class ImunizacijaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImunizacijaApplication.class, args);
	}

	@Autowired
	public EmailService emailService;

	@Bean
	public ApplicationRunner applicationRunner() {
		return args -> emailService.sendEmail(new SendEmailRequest("dusanerdeljan99@gmail.com", "Imunizacija service", "Neka poruka"));
	}
}
