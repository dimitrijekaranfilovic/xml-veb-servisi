package rs.vakcinacija.sluzbenici.vakcinacionipunkt.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InteresovanjeDodeljenTerminEvent {
    private UUID interesovanjeId;
    private Date dodeljeniDatum;
}
