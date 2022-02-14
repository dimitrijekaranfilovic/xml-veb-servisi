package rs.vakcinacija.imunizacija.zahtevzasertifikat.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.model.RDFField;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "odbijenica")
@XmlType(name = "TOdbijenica", propOrder = {"razlog", "datumOdbijanja"})
@Getter
@Setter
public class Odbijenica extends RDFField {
    @XmlElement(name = "razlog", required = true)
    private RDFString razlog;

    @XmlElement(name = "datum_odbijanja", required = true)
    private RDFDate datumOdbijanja;

    public Odbijenica() {}

    public Odbijenica(RDFString razlog, RDFDate datumOdbijanja) {
        this.razlog = razlog;
        this.datumOdbijanja = datumOdbijanja;
    }
}
