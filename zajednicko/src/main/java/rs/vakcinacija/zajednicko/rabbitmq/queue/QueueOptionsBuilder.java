package rs.vakcinacija.zajednicko.rabbitmq.queue;

import lombok.Getter;
import rs.vakcinacija.zajednicko.rabbitmq.event.MessageEvent;

@Getter
public class QueueOptionsBuilder {
    private String queueName;
    private boolean durable = false;
    private boolean exclusive = false;
    private boolean autoDelete = false;

    public static <T extends MessageEvent> QueueOptionsBuilder of(Class<T> clazz) {
        var builder = new QueueOptionsBuilder();
        builder.queueName = clazz.getSimpleName();
        return builder;
    }

    public static <T extends MessageEvent> QueueOptionsBuilder defaultOptions(Class<T> clazz) {
        return QueueOptionsBuilder.of(clazz).durable();
    }

    public QueueOptionsBuilder durable() {
        this.durable = true;
        return this;
    }

    public QueueOptionsBuilder exclusive() {
        this.exclusive = true;
        return this;
    }

    public QueueOptionsBuilder autoDelete() {
        this.autoDelete = true;
        return this;
    }
}
