package rs.vakcinacija.imunizacija.saglasnost.model;

import rs.vakcinacija.zajednicko.model.RDFField;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "srpski_drzavljanin")
@XmlType(name = "TSrpskiDrzavljanin", propOrder = {"jmbg"})
public class SrpskiDrzavljanin {

    @XmlElement(name = "jmbg", required = true)
    private RDFField<String> jmbg;

    public SrpskiDrzavljanin() {}

    public SrpskiDrzavljanin(RDFField<String> jmbg) {
        this.jmbg = jmbg;
    }

    public RDFField<String> getJmbg() {
        return jmbg;
    }

    public void setJmbg(RDFField<String> jmbg) {
        this.jmbg = jmbg;
    }
}
