package rs.vakcinacija.imunizacija.interesovanje.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "licne_informacije")
@XmlType(name = "TLicneInformacije", propOrder = {"drzavljanstvo", "jmbg", "kontakt", "punoIme", "davalacKrvi"})
public class LicneInformacije {

    @XmlElement(name = "drzavljanstvo", required = true)
    private String drzavljanstvo;

    @XmlElement(name = "jmbg", required = true)
    private String jmbg;

    @XmlElement(name = "kontakt", required = true)
    private Kontakt kontakt;

    @XmlElement(name = "puno_ime", required = true)
    private PunoIme punoIme;

    @XmlElement(name = "davalac_krvi", required = true)
    private Boolean davalacKrvi;


    public LicneInformacije() {
    }

    public LicneInformacije(String drzavljanstvo, String jmbg, Kontakt kontakt, PunoIme punoIme,
                            Boolean davalacKrvi) {
        this.drzavljanstvo = drzavljanstvo;
        this.jmbg = jmbg;
        this.kontakt = kontakt;
        this.punoIme = punoIme;
        this.davalacKrvi = davalacKrvi;
    }


    public String getDrzavljanstvo() {
        return drzavljanstvo;
    }

    public void setDrzavljanstvo(String drzavljanstvo) {
        this.drzavljanstvo = drzavljanstvo;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public Kontakt getKontakt() {
        return kontakt;
    }

    public void setKontakt(Kontakt kontakt) {
        this.kontakt = kontakt;
    }

    public PunoIme getPunoIme() {
        return punoIme;
    }

    public void setPunoIme(PunoIme punoIme) {
        this.punoIme = punoIme;
    }

    public Boolean getDavalacKrvi() {
        return davalacKrvi;
    }

    public void setDavalacKrvi(Boolean davalacKrvi) {
        this.davalacKrvi = davalacKrvi;
    }

}
