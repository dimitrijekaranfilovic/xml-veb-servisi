package rs.vakcinacija.imunizacija.saglasnost.model;

import rs.vakcinacija.zajednicko.model.DozaDetaljnije;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vakcina")
@XmlType(name = "TVakcina", propOrder = {"ekstremitet", "nuspojava"})
public class Vakcina extends DozaDetaljnije {

    @XmlElement(name = "ekstremitet", required = true)
    private String ekstremitet;

    @XmlElement(name = "nuspojava", required = true)
    private String nuspojava;

    public Vakcina() {
        super();
    }

    public Vakcina(Integer brojDoze,
                   Date datumDavanja,
                   String brojSerije,
                   String tip,
                   String proizvodjac,
                   String ekstremitet,
                   String nuspojava) {
        super(brojDoze, datumDavanja, brojSerije, tip, proizvodjac);
        this.ekstremitet = ekstremitet;
        this.nuspojava = nuspojava;
    }

    public String getEkstremitet() {
        return ekstremitet;
    }

    public void setEkstremitet(String ekstremitet) {
        this.ekstremitet = ekstremitet;
    }

    public String getNuspojava() {
        return nuspojava;
    }

    public void setNuspojava(String nuspojava) {
        this.nuspojava = nuspojava;
    }
}
