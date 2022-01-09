package rs.vakcinacija.imunizacija.saglasnost.model;

import rs.vakcinacija.zajednicko.model.DozaDetaljnije;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vakcina")
@XmlType(name = "TVakcina", propOrder = {"ekstremitet", "nuspojava"})
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

    public RDFField<String> getEkstremitet() {
        return ekstremitet;
    }

    public void setEkstremitet(RDFField<String> ekstremitet) {
        this.ekstremitet = ekstremitet;
    }

    public RDFField<String> getNuspojava() {
        return nuspojava;
    }

    public void setNuspojava(RDFField<String> nuspojava) {
        this.nuspojava = nuspojava;
    }
}
