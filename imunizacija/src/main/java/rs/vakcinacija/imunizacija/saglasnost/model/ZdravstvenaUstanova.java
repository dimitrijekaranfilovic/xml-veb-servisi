package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "zdravstvena_ustanova")
@XmlType(name = "TZdravstvenaUstanova", propOrder = {"naziv", "punkt"})
@Getter
@Setter
public class ZdravstvenaUstanova {

    @XmlElement(name = "naziv", required = true)
    private RDFField<String> naziv;

    @XmlElement(name = "punkt", required = true)
    private RDFField<Integer> punkt;

    public ZdravstvenaUstanova() {
    }

    public ZdravstvenaUstanova(RDFField<String> naziv, RDFField<Integer> punkt) {
        this.naziv = naziv;
        this.punkt = punkt;
    }
}
