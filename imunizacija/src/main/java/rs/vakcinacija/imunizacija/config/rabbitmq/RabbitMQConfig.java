package rs.vakcinacija.imunizacija.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2XmlMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.vakcinacija.imunizacija.MyCustomEvent;
import rs.vakcinacija.zajednicko.rabbitmq.event.MessageEvent;
import rs.vakcinacija.zajednicko.rabbitmq.queue.QueueOptionsBuilder;

@Configuration
public class RabbitMQConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2XmlMessageConverter();
    }

    // ===================== CONFIGURE RABBITMQ QUEUES HERE =====================
    private <T extends MessageEvent> Queue buildQueue(Class<T> event) {
        var options = QueueOptionsBuilder.defaultOptions(event);
        return new Queue(options.getQueueName(), options.isDurable(), options.isExclusive(), options.isAutoDelete());
    }

    @Bean
    public Queue myCustomEventQueue() {
        return buildQueue(MyCustomEvent.class);
    }

}
