package rs.vakcinacija.zajednicko.rabbitmq.event;

public abstract class AbstractMessageEvent implements MessageEvent {
    @Override
    public String queue() {
        return getClass().getSimpleName();
    }
}
