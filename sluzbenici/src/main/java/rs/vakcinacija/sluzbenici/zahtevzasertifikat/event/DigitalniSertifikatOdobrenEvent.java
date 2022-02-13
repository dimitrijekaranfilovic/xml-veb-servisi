package rs.vakcinacija.sluzbenici.zahtevzasertifikat.event;

import lombok.*;
import rs.vakcinacija.zajednicko.rabbitmq.event.AbstractMessageEvent;

import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DigitalniSertifikatOdobrenEvent extends AbstractMessageEvent {
    private UUID requestId;
    private UUID digitalCertificateId;
}
