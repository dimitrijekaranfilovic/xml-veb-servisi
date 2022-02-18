package rs.vakcinacija.imunizacija.interesovanje.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InteresovanjePodnetoEvent {
    private String mesto;
    private Collection<String> odabraneVakcine;
    private String email;
    private UUID interesovanjeId;
}
