package rs.vakcinacija.sluzbenici.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "strani_drzavljanin")
@XmlType(name = "TStraniDrzavljanin", propOrder = {"nazivDrzavljanstva", "brojPasosa"})
@Getter
@Setter
public class StraniDrzavljanin extends RDFField {

    @XmlElement(name = "naziv_drzavljanstva", required = true)
    private RDFString nazivDrzavljanstva;

    @XmlElement(name = "broj_pasosa", required = true)
    private RDFString brojPasosa;

    public StraniDrzavljanin() {}

    public StraniDrzavljanin(RDFString nazivDrzave, RDFString brojPasosa) {
        this.nazivDrzavljanstva = nazivDrzave;
        this.brojPasosa = brojPasosa;
    }
}
