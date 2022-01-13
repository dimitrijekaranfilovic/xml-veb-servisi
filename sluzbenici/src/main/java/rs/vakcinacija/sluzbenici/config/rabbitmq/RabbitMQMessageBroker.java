package rs.vakcinacija.sluzbenici.config.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.vakcinacija.zajednicko.rabbitmq.MessageBroker;

@Component
public class RabbitMQMessageBroker implements MessageBroker {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQMessageBroker(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void send(String queueName, Object payload) {
        rabbitTemplate.convertAndSend(queueName, payload);
    }
}
