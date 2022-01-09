package rs.vakcinacija.imunizacija.saglasnost.model;

import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "telefon")
@XmlType(name = "TTelefon", propOrder = {"brojFiksnog", "brojMobilnog"})
public class Telefon {

    @XmlElement(name = "broj_fiksnog", required = true)
    private RDFField<String> brojFiksnog;

    @XmlElement(name = "broj_mobilnog", required = true)
    private RDFField<String> brojMobilnog;

    public Telefon() {}

    public Telefon(RDFField<String> brojFiksnog, RDFField<String> brojMobilnog) {
        this.brojFiksnog = brojFiksnog;
        this.brojMobilnog = brojMobilnog;
    }

    public RDFField<String> getBrojFiksnog() {
        return brojFiksnog;
    }

    public void setBrojFiksnog(RDFField<String> brojFiksnog) {
        this.brojFiksnog = brojFiksnog;
    }

    public RDFField<String> getBrojMobilnog() {
        return brojMobilnog;
    }

    public void setBrojMobilnog(RDFField<String> brojMobilnog) {
        this.brojMobilnog = brojMobilnog;
    }
}
