package rs.vakcinacija.imunizacija.saglasnost.model;

import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "drzavljanstvo")
@XmlType(name = "TDrzavljanstvo", propOrder = {"srpskiDrzavljanin", "straniDrzavljanin"})
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

    public RDFField<SrpskiDrzavljanin> getSrpskiDrzavljanin() {
        return srpskiDrzavljanin;
    }

    public void setSrpskiDrzavljanin(RDFField<SrpskiDrzavljanin> srpskiDrzavljanin) {
        this.srpskiDrzavljanin = srpskiDrzavljanin;
    }

    public RDFField<StraniDrzavljanin> getStraniDrzavljanin() {
        return straniDrzavljanin;
    }

    public void setStraniDrzavljanin(RDFField<StraniDrzavljanin> straniDrzavljanin) {
        this.straniDrzavljanin = straniDrzavljanin;
    }
}
