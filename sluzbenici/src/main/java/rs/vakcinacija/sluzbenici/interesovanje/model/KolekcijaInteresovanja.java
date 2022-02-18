package rs.vakcinacija.sluzbenici.interesovanje.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "interesovanja")
@XmlAccessorType(XmlAccessType.FIELD)
public class KolekcijaInteresovanja {

    @XmlElement(name = "interesovanje")
    private List<Interesovanje> interesovanja;

    public KolekcijaInteresovanja() {
    }

    public KolekcijaInteresovanja(List<Interesovanje> interesovanja) {
        this.interesovanja = interesovanja;
    }

    public static KolekcijaInteresovanja of(List<Interesovanje> interesovanja) {
        return new KolekcijaInteresovanja(interesovanja);
    }
}
