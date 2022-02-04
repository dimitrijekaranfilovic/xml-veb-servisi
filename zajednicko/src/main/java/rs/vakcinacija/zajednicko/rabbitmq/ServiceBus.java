package rs.vakcinacija.zajednicko.rabbitmq;

import rs.vakcinacija.zajednicko.rabbitmq.event.MessageEvent;

public interface ServiceBus {

    <T extends MessageEvent> void publish(T event);

}
