package rs.vakcinacija.sluzbenici.vakcinacionipunkt.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.vakcinacija.zajednicko.rabbitmq.event.AbstractMessageEvent;

import java.util.Collection;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InteresovanjePodnetoEvent extends AbstractMessageEvent {
    private String mesto;
    private Collection<String> odabraneVakcine;
    private String email;
    private UUID interesovanjeId;
}
