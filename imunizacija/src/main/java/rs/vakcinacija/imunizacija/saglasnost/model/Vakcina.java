package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.DozaDetaljnije;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vakcina")
@XmlType(name = "TVakcina", propOrder = {"ekstremitet", "nuspojava"})
@Getter
@Setter
public class Vakcina extends DozaDetaljnije {

    @XmlElement(name = "ekstremitet", required = true)
    private RDFField<String> ekstremitet;

    @XmlElement(name = "nuspojava", required = true)
    private RDFField<String> nuspojava;

    public Vakcina() {
        super();
    }

    public Vakcina(RDFField<Integer> brojDoze,
                   RDFField<Date> datumDavanja,
                   RDFField<String> brojSerije,
                   RDFField<String> tip,
                   RDFField<String> proizvodjac,
                   RDFField<String> ekstremitet,
                   RDFField<String> nuspojava) {
        super(brojDoze, datumDavanja, brojSerije, tip, proizvodjac);
        this.ekstremitet = ekstremitet;
        this.nuspojava = nuspojava;
    }
}
