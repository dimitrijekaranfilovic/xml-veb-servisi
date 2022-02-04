package rs.vakcinacija.sluzbenici.config.rabbitmq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.vakcinacija.zajednicko.rabbitmq.MessageBroker;
import rs.vakcinacija.zajednicko.rabbitmq.ServiceBus;
import rs.vakcinacija.zajednicko.rabbitmq.bus.MessageBrokerServiceBus;

@Configuration
public class ServiceBusConfig {
    @Bean
    public ServiceBus serviceBus(MessageBroker messageBroker) {
        return new MessageBrokerServiceBus(messageBroker);
    }
}
