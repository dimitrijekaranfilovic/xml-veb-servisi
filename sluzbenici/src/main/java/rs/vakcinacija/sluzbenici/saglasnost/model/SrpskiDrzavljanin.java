package rs.vakcinacija.sluzbenici.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "srpski_drzavljanin")
@XmlType(name = "TSrpskiDrzavljanin", propOrder = {"jmbg"})
@Getter
@Setter
public class SrpskiDrzavljanin extends RDFField {

    @XmlElement(name = "jmbg", required = true)
    private RDFString jmbg;

    public SrpskiDrzavljanin() {}

    public SrpskiDrzavljanin(RDFString jmbg) {
        this.jmbg = jmbg;
    }
}
