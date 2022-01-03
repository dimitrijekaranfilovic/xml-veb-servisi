package rs.vakcinacija.imunizacija.saglasnost.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "srpski_drzavljanin")
@XmlType(name = "TSrpskiDrzavljanin", propOrder = {"jmbg"})
public class SrpskiDrzavljanin {

    @XmlElement(name = "jmbg", required = true)
    private String jmbg;

    public SrpskiDrzavljanin() {}

    public SrpskiDrzavljanin(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }
}
