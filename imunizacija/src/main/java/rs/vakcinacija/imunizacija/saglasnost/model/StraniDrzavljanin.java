package rs.vakcinacija.imunizacija.saglasnost.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "strani_drzavljanin")
@XmlType(name = "TStraniDrzavljanin", propOrder = {"nazivDrzave", "brojPasosa"})
public class StraniDrzavljanin {

    @XmlElement(name = "naziv_drzave", required = true)
    private String nazivDrzave;

    @XmlElement(name = "broj_pasosa", required = true)
    private String brojPasosa;

    public StraniDrzavljanin() {}

    public StraniDrzavljanin(String nazivDrzave, String brojPasosa) {
        this.nazivDrzave = nazivDrzave;
        this.brojPasosa = brojPasosa;
    }

    public String getNazivDrzave() {
        return nazivDrzave;
    }

    public void setNazivDrzave(String nazivDrzave) {
        this.nazivDrzave = nazivDrzave;
    }

    public String getBrojPasosa() {
        return brojPasosa;
    }

    public void setBrojPasosa(String brojPasosa) {
        this.brojPasosa = brojPasosa;
    }
}
