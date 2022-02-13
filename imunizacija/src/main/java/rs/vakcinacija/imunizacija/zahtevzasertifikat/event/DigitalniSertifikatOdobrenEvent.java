package rs.vakcinacija.imunizacija.zahtevzasertifikat.event;

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
public class DigitalniSertifikatOdobrenEvent extends AbstractMessageEvent {
    private UUID requestId;
    private UUID digitalCertificateId;
}
