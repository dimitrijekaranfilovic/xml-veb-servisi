package rs.vakcinacija.sluzbenici.vakcinacionipunkt.model;


import lombok.Getter;
import lombok.Setter;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Collection;

@XmlRootElement(name = "termini")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTermini")
@Getter
@Setter
public class Termini {


    @XmlElement(name = "termin")
    private Collection<XMLGregorianCalendar> termini;

    public Termini(Collection<XMLGregorianCalendar> termini) {
        this.termini = termini;
    }

    public Termini() {
    }

}
