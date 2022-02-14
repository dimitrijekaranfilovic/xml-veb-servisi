package rs.vakcinacija.sluzbenici.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;
import rs.vakcinacija.zajednicko.model.RDFInteger;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "zdravstvena_ustanova")
@XmlType(name = "TZdravstvenaUstanova", propOrder = {"naziv", "punkt"})
@Getter
@Setter
public class ZdravstvenaUstanova extends RDFField {

    @XmlElement(name = "naziv", required = true)
    private RDFString naziv;

    @XmlElement(name = "punkt", required = true)
    private RDFInteger punkt;

    public ZdravstvenaUstanova() {
    }

    public ZdravstvenaUstanova(RDFString naziv, RDFInteger punkt) {
        this.naziv = naziv;
        this.punkt = punkt;
    }
}
