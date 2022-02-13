package rs.vakcinacija.sluzbenici.zahtevzasertifikat.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.vakcinacija.zajednicko.rabbitmq.event.AbstractMessageEvent;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZahtevZaSertifikatOdbijenEvent extends AbstractMessageEvent {
    private UUID id;
    private String reason;
}
