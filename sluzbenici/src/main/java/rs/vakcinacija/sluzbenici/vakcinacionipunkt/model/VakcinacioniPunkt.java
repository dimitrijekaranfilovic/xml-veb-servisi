package rs.vakcinacija.sluzbenici.vakcinacionipunkt.model;


import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.BaseDocument;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "vakcinacioni_punkt")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class VakcinacioniPunkt extends BaseDocument {


    @XmlElement(name = "naziv_punkta")
    private String nazivPunkta;

    @XmlElement(name = "termini")
    private Termini termini;

    @XmlElement(name = "dostupne_vakcine")
    private DostupneVakcine dostupneVakcine;

    public VakcinacioniPunkt(String nazivPunkta) {
        this.nazivPunkta = nazivPunkta;
        this.termini = termini;
        this.dostupneVakcine = dostupneVakcine;
    }

    public VakcinacioniPunkt() {
        super();
    }
}
