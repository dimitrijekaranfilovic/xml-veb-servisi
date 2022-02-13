package rs.vakcinacija.sluzbenici.vakcinacionipunkt.model;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Collection;

@XmlRootElement(name = "termini")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTermini")
@Getter
@Setter
public class Termini {


    @XmlElement(name = "termin")
    private Collection<Termin> termini;

    public Termini(Collection<Termin> termini) {
        this.termini = termini;
    }

    public Termini() {
    }
}
