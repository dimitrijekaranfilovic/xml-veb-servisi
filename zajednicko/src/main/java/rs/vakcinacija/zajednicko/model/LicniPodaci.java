package rs.vakcinacija.zajednicko.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "licni_podaci")
@XmlType(name = "TLicni_podaci", propOrder = {"jmbg", "pol"})
public class LicniPodaci extends ImePrezime {

    @XmlElement(name = "jmbg", required = true)
    private String jmbg;

    @XmlElement(name = "pol", required = true)
    private String pol;

    public LicniPodaci() {
    }

    public LicniPodaci(String ime, String prezime, String jmbg, String pol) {
        super(ime, prezime);
        this.jmbg = jmbg;
        this.pol = pol;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }
}

