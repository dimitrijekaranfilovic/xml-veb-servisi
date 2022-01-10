package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.DozaDetaljnije;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.model.RDFInteger;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vakcina")
@XmlType(name = "TVakcina", propOrder = {"ekstremitet", "nuspojava"})
@Getter
@Setter
public class Vakcina extends DozaDetaljnije {

    @XmlElement(name = "ekstremitet", required = true)
    private RDFString ekstremitet;

    @XmlElement(name = "nuspojava", required = true)
    private RDFString nuspojava;

    public Vakcina() {
        super();
    }

    public Vakcina(RDFInteger brojDoze,
                   RDFDate datumDavanja,
                   RDFString brojSerije,
                   RDFString tip,
                   RDFString proizvodjac,
                   RDFString ekstremitet,
                   RDFString nuspojava) {
        super(brojDoze, datumDavanja, brojSerije, tip, proizvodjac);
        this.ekstremitet = ekstremitet;
        this.nuspojava = nuspojava;
    }
}
