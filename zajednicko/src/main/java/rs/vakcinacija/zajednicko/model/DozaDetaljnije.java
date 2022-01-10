package rs.vakcinacija.zajednicko.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "doza_detaljnije")
@XmlType(name = "TDoza_detaljnije", propOrder = {"tip", "proizvodjac"})
@Getter
@Setter
public class DozaDetaljnije extends Doza {

    @XmlElement(name = "tip", required = true)
    private RDFField<String> tip;

    @XmlElement(name = "proizvodjac", required = true)
    private RDFField<String> proizvodjac;

    public DozaDetaljnije() {
    }

    public DozaDetaljnije(RDFField<Integer> brojDoze, RDFField<Date> datumDavanja,
                          RDFField<String> brojSerije, RDFField<String> tip, RDFField<String> proizvodjac) {
        super(brojDoze, datumDavanja, brojSerije);
        this.tip = tip;
        this.proizvodjac = proizvodjac;
    }
}
