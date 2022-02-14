package rs.vakcinacija.sluzbenici.vakcinacionipunkt.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter @Setter
@XmlRootElement(name = "vakcinacioni_punktovi")
@XmlAccessorType(XmlAccessType.FIELD)
public class KolekcijaVakcinacionihPunktova {

    @XmlElement(name = "vakcinacioni_punkt")
    private List<VakcinacioniPunkt> vakcinacioniPunktovi;

    public KolekcijaVakcinacionihPunktova() {
    }

    public KolekcijaVakcinacionihPunktova(List<VakcinacioniPunkt> vakcinacioniPunktovi) {
        this.vakcinacioniPunktovi = vakcinacioniPunktovi;
    }

    public static KolekcijaVakcinacionihPunktova of(List<VakcinacioniPunkt> kolekcijaVakcinacionihPunktova) {
        return new KolekcijaVakcinacionihPunktova(kolekcijaVakcinacionihPunktova);
    }


}
