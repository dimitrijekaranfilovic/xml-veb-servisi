package rs.vakcinacija.sluzbenici.zahtevzasertifikat.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZahtevZaSertifikatOdbijenEvent {
    private String reason;
    private Date rejectionDate;
}
