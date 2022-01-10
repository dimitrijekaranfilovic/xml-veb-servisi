package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "srpski_drzavljanin")
@XmlType(name = "TSrpskiDrzavljanin", propOrder = {"jmbg"})
@Getter
@Setter
public class SrpskiDrzavljanin {

    @XmlElement(name = "jmbg", required = true)
    private RDFField<String> jmbg;

    public SrpskiDrzavljanin() {}

    public SrpskiDrzavljanin(RDFField<String> jmbg) {
        this.jmbg = jmbg;
    }
}
