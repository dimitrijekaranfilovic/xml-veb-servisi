package rs.vakcinacija.imunizacija.saglasnost.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "drzavljanstvo")
@XmlType(name = "TDrzavljanstvo", propOrder = {"srpskiDrzavljanin", "straniDrzavljanin"})
public class Drzavljanstvo {

    @XmlElement(name = "srpski_drzavljanin", required = false)
    private SrpskiDrzavljanin srpskiDrzavljanin;

    @XmlElement(name = "strani_drzavljanin", required = false)
    private StraniDrzavljanin straniDrzavljanin;

    public Drzavljanstvo() {}

    public Drzavljanstvo(SrpskiDrzavljanin srpskiDrzavljanin, StraniDrzavljanin straniDrzavljanin) {
        this.srpskiDrzavljanin = srpskiDrzavljanin;
        this.straniDrzavljanin = straniDrzavljanin;
    }

    public SrpskiDrzavljanin getSrpskiDrzavljanin() {
        return srpskiDrzavljanin;
    }

    public void setSrpskiDrzavljanin(SrpskiDrzavljanin srpskiDrzavljanin) {
        this.srpskiDrzavljanin = srpskiDrzavljanin;
    }

    public StraniDrzavljanin getStraniDrzavljanin() {
        return straniDrzavljanin;
    }

    public void setStraniDrzavljanin(StraniDrzavljanin straniDrzavljanin) {
        this.straniDrzavljanin = straniDrzavljanin;
    }
}
