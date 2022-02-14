package rs.vakcinacija.sluzbenici.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "drzavljanstvo")
@XmlType(name = "TDrzavljanstvo", propOrder = {"srpskiDrzavljanin", "straniDrzavljanin"})
@Getter
@Setter
public class Drzavljanstvo extends RDFField {

    @XmlElement(name = "srpski_drzavljanin", required = false)
    private SrpskiDrzavljanin srpskiDrzavljanin;

    @XmlElement(name = "strani_drzavljanin", required = false)
    private StraniDrzavljanin straniDrzavljanin;

    public Drzavljanstvo() {}

    public Drzavljanstvo(SrpskiDrzavljanin srpskiDrzavljanin, StraniDrzavljanin straniDrzavljanin) {
        this.srpskiDrzavljanin = srpskiDrzavljanin;
        this.straniDrzavljanin = straniDrzavljanin;
    }
}
