package rs.vakcinacija.zajednicko.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "doza_detaljnije")
@XmlType(name = "TDoza_detaljnije", propOrder = {"tip", "proizvodjac"})
@Getter
@Setter
public class DozaDetaljnije extends Doza {

    @XmlElement(name = "tip", required = true)
    private RDFString tip;

    @XmlElement(name = "proizvodjac", required = true)
    private RDFString proizvodjac;

    public DozaDetaljnije() {
    }

    public DozaDetaljnije(RDFInteger brojDoze, RDFDate datumDavanja,
                          RDFString brojSerije, RDFString tip, RDFString proizvodjac) {
        super(brojDoze, datumDavanja, brojSerije);
        this.tip = tip;
        this.proizvodjac = proizvodjac;
    }
}
