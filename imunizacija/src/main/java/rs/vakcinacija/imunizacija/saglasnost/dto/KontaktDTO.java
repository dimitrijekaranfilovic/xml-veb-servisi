package rs.vakcinacija.imunizacija.saglasnost.dto;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "kontakt")
@XmlType(name = "TKontakt", propOrder = {"brojFiksnog", "brojMobilnog", "email"})
@Getter
@Setter
public class KontaktDTO {

    @XmlElement(name = "brojFiksnog", required = true)
    private RDFString brojFiksnog;

    @XmlElement(name = "brojMobilnog", required = true)
    private RDFString brojMobilnog;

    @XmlElement(name = "email", required = true)
    private RDFString email;

    public KontaktDTO() {}

    public KontaktDTO(RDFString brojFiksnog, RDFString brojMobilnog, RDFString email) {
        this.brojFiksnog = brojFiksnog;
        this.brojMobilnog = brojMobilnog;
        this.email = email;
    }
}
