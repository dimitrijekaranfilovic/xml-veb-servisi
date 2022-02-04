package rs.vakcinacija.sluzbenici;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.rabbitmq.event.AbstractMessageEvent;

@Getter
@Setter
public class MyCustomEvent extends AbstractMessageEvent {
    private String myData;
}
