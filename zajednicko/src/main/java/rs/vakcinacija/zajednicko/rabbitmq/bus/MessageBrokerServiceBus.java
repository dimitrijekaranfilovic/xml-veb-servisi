package rs.vakcinacija.zajednicko.rabbitmq.bus;

import rs.vakcinacija.zajednicko.rabbitmq.MessageBroker;
import rs.vakcinacija.zajednicko.rabbitmq.ServiceBus;
import rs.vakcinacija.zajednicko.rabbitmq.event.MessageEvent;

public class MessageBrokerServiceBus implements ServiceBus {
    private final MessageBroker messageBroker;

    public MessageBrokerServiceBus(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
    }

    @Override
    public <T extends MessageEvent> void publish(T event) {
        var queueName = event.queue();
        messageBroker.send(queueName, event);
    }
}
