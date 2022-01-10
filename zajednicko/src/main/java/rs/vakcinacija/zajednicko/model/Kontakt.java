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
public class Kontakt {

    @XmlElement(name = "broj_fiksnog", required = true)
    private RDFField<String> brojFiksnog;

    @XmlElement(name = "broj_mobilnog", required = true)
    private RDFField<String> brojMobilnog;

    @XmlElement(name = "email", required = true)
    private RDFField<String> email;


    public Kontakt() {
    }

    public Kontakt(RDFField<String> brojFiksnog, RDFField<String> brojMobilnog, RDFField<String> email) {
        this.brojFiksnog = brojFiksnog;
        this.brojMobilnog = brojMobilnog;
        this.email = email;
    }
}