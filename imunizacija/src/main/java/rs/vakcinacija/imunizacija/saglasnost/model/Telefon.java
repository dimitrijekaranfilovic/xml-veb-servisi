package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "telefon")
@XmlType(name = "TTelefon", propOrder = {"brojFiksnog", "brojMobilnog"})
@Getter
@Setter
public class Telefon extends RDFField {

    @XmlElement(name = "broj_fiksnog", required = true)
    private RDFString brojFiksnog;

    @XmlElement(name = "broj_mobilnog", required = true)
    private RDFString brojMobilnog;

    public Telefon() {}

    public Telefon(RDFString brojFiksnog, RDFString brojMobilnog) {
        this.brojFiksnog = brojFiksnog;
        this.brojMobilnog = brojMobilnog;
    }
}
