package rs.vakcinacija.zajednicko.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "kontakt")
@XmlType(name = "TKontakt", propOrder = {"brojFiksnog", "brojMobilnog", "email"})
@Getter
@Setter
public class Kontakt extends RDFField {

    @XmlElement(name = "broj_fiksnog", required = true)
    private RDFString brojFiksnog;

    @XmlElement(name = "broj_mobilnog", required = true)
    private RDFString brojMobilnog;

    @XmlElement(name = "email", required = true)
    private RDFString email;


    public Kontakt() {
    }

    public Kontakt(RDFString brojFiksnog, RDFString brojMobilnog, RDFString email) {
        this.brojFiksnog = brojFiksnog;
        this.brojMobilnog = brojMobilnog;
        this.email = email;
    }
}