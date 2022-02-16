package rs.vakcinacija.sluzbenici.vakcinacionipunkt.model;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@XmlRootElement(name = "termini")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTermini")
@Getter
@Setter
public class Termini {


    @XmlElement(name = "termin")
    private Collection<Date> termini;

    public Termini(Collection<Date> termini) {
        this.termini = termini;
    }

    public Termini() {
        super();
        this.termini = new ArrayList<>();
    }

}
