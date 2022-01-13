package rs.vakcinacija.zajednicko.rabbitmq;

public interface MessageBroker {

    void send(String queueName, Object payload);
}
