package rs.vakcinacija.imunizacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import rs.vakcinacija.zajednicko.rabbitmq.ServiceBus;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ImunizacijaApplication {

    @Autowired
    public ServiceBus serviceBus;

    public static void main(String[] args) {
        SpringApplication.run(ImunizacijaApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            for (int i = 0; i < 10; i++) {
                var event = new MyCustomEvent();
                event.setMyData(String.format("hello: %d!", i));
                serviceBus.publish(event);
            }
        };
    }
}
