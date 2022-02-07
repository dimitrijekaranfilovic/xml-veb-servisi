package rs.vakcinacija.sluzbenici;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@EnableFeignClients
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class SluzbeniciApplication {

	public static void main(String[] args) {
		SpringApplication.run(SluzbeniciApplication.class, args);
	}

	@RabbitListener(queues = "MyCustomEvent")
	public void listen(MyCustomEvent event) {
		log.info(String.format("Got message from MyCustomEvent: '%s'", event.getMyData()));
	}
}
