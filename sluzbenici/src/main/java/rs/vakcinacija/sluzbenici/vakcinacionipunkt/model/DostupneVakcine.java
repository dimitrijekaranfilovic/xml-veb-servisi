package rs.vakcinacija.sluzbenici.vakcinacionipunkt.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement(name = "dostupne_vakcine")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class DostupneVakcine {


    @XmlElement(name = "dostupna_vakcina")
    private Collection<DostupnaVakcina> dostupneVakcine;

    public DostupneVakcine(Collection<DostupnaVakcina> dostupneVakcine) {
        this.dostupneVakcine = dostupneVakcine;
    }

    public DostupneVakcine() {
        super();
    }
}
