package rs.vakcinacija.sluzbenici.zahtevzasertifikat.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DigitalniSertifikatOdobrenEvent {
    private UUID digitalCertificateId;
}
