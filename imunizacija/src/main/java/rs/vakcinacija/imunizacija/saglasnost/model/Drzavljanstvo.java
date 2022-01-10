package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "drzavljanstvo")
@XmlType(name = "TDrzavljanstvo", propOrder = {"srpskiDrzavljanin", "straniDrzavljanin"})
@Getter
@Setter
public class Drzavljanstvo {

    @XmlElement(name = "srpski_drzavljanin", required = false)
    private RDFField<SrpskiDrzavljanin> srpskiDrzavljanin;

    @XmlElement(name = "strani_drzavljanin", required = false)
    private RDFField<StraniDrzavljanin> straniDrzavljanin;

    public Drzavljanstvo() {}

    public Drzavljanstvo(RDFField<SrpskiDrzavljanin> srpskiDrzavljanin,
                         RDFField<StraniDrzavljanin> straniDrzavljanin) {
        this.srpskiDrzavljanin = srpskiDrzavljanin;
        this.straniDrzavljanin = straniDrzavljanin;
    }
}
