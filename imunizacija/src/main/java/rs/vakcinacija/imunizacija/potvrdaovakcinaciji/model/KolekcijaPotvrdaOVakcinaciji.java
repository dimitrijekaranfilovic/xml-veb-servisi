package rs.vakcinacija.imunizacija.potvrdaovakcinaciji.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "potvrde_o_vakcinaciji")
@XmlAccessorType(XmlAccessType.FIELD)
public class KolekcijaPotvrdaOVakcinaciji {

    @XmlElement(name = "potvrda_o_vakcinaciji")
    private List<PotvrdaOVakcinaciji> potvrde;

    public KolekcijaPotvrdaOVakcinaciji() {
    }

    public KolekcijaPotvrdaOVakcinaciji(List<PotvrdaOVakcinaciji> potvrde) {
        this.potvrde = potvrde;
    }

    public static KolekcijaPotvrdaOVakcinaciji of(List<PotvrdaOVakcinaciji> potvrde) {
        return new KolekcijaPotvrdaOVakcinaciji(potvrde);
    }
}