package rs.vakcinacija.sluzbenici.saglasnost.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "saglasnosti")
@XmlAccessorType(XmlAccessType.FIELD)
public class KolekcijaSaglasnosti {

    @XmlElement(name = "saglasnost")
    private List<SaglasnostZaSprovodjenjeImunizacije> saglasnosti;

    public KolekcijaSaglasnosti() {
    }

    public KolekcijaSaglasnosti(List<SaglasnostZaSprovodjenjeImunizacije> saglasnosti) {
        this.saglasnosti = saglasnosti;
    }

    public static KolekcijaSaglasnosti of(List<SaglasnostZaSprovodjenjeImunizacije> saglasnosti) {
        return new KolekcijaSaglasnosti(saglasnosti);
    }
}
