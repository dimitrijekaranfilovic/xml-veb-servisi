package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "strani_drzavljanin")
@XmlType(name = "TStraniDrzavljanin", propOrder = {"nazivDrzave", "brojPasosa"})
@Getter
@Setter
public class StraniDrzavljanin extends RDFField {

    @XmlElement(name = "naziv_drzave", required = true)
    private RDFString nazivDrzave;

    @XmlElement(name = "broj_pasosa", required = true)
    private RDFString brojPasosa;

    public StraniDrzavljanin() {}

    public StraniDrzavljanin(RDFString nazivDrzave, RDFString brojPasosa) {
        this.nazivDrzave = nazivDrzave;
        this.brojPasosa = brojPasosa;
    }
}
