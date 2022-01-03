package rs.vakcinacija.imunizacija.saglasnost.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "adresa")
@XmlType(name = "TAdresa", propOrder = {"ulica", "brojZgrade", "mesto", "opstina"})
public class Adresa {

    @XmlElement(name = "ulica", required = true)
    private String ulica;

    @XmlElement(name = "broj_zgrade", required = true)
    private String brojZgrade;

    @XmlElement(name = "mesto", required = true)
    private String mesto;

    @XmlElement(name = "opstina", required = true)
    private String opstina;

    public Adresa() {}

    public Adresa(String ulica, String brojZgrade, String mesto, String opstina) {
        this.ulica = ulica;
        this.brojZgrade = brojZgrade;
        this.mesto = mesto;
        this.opstina = opstina;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getBrojZgrade() {
        return brojZgrade;
    }

    public void setBrojZgrade(String brojZgrade) {
        this.brojZgrade = brojZgrade;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getOpstina() {
        return opstina;
    }

    public void setOpstina(String opstina) {
        this.opstina = opstina;
    }
}
