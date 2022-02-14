package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "obrazci_saglasnosti")
@XmlAccessorType(XmlAccessType.FIELD)
public class KolekcijaObrazacaSaglasnosti {

    @XmlElement(name = "saglasnost_za_sprovodjenje_preporucene_imunizacije")
    private List<SaglasnostZaSprovodjenjeImunizacije> saglasnosti;

    public KolekcijaObrazacaSaglasnosti() {
    }

    public KolekcijaObrazacaSaglasnosti(List<SaglasnostZaSprovodjenjeImunizacije> saglasnosti) {
        this.saglasnosti = saglasnosti;
    }

    public static KolekcijaObrazacaSaglasnosti of(List<SaglasnostZaSprovodjenjeImunizacije> saglasnosti) {
        return new KolekcijaObrazacaSaglasnosti(saglasnosti);
    }
}
