package rs.vakcinacija.sluzbenici.vakcinacionipunkt.model;


import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.BaseDocument;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "vakcinacioni_punkt")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class VakcinacioniPunkt extends BaseDocument {

    @XmlElement(name = "naziv_punkta", required = true)
    private String nazivPunkta;

    @XmlElement(name = "termini")
    private Termini termini;

    @XmlElement(name = "dostupne_vakcine")
    private DostupneVakcine dostupneVakcine;

    @XmlElement(name = "zainteresovani_pacijenti")
    private ZainteresovaniPacijenti zainteresovaniPacijenti;

    public VakcinacioniPunkt(String nazivPunkta, Termini termini, DostupneVakcine dostupneVakcine) {
        this.nazivPunkta = nazivPunkta;
        this.termini = termini;
        this.dostupneVakcine = dostupneVakcine;
    }

    public VakcinacioniPunkt() {
        super();
        this.dostupneVakcine = new DostupneVakcine();
        this.termini = new Termini();
        this.zainteresovaniPacijenti = new ZainteresovaniPacijenti();
    }

    public void addTermin(Date date) {
        this.getTermini().getTermini().add(date);
    }

    public void addDostupnaVakcina(DostupnaVakcina dostupnaVakcina){
        this.getDostupneVakcine().getDostupneVakcine().add(dostupnaVakcina);
    }
}
