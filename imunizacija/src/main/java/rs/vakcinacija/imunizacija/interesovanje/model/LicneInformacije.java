package rs.vakcinacija.imunizacija.interesovanje.model;


import rs.vakcinacija.zajednicko.model.ImePrezime;
import rs.vakcinacija.zajednicko.model.Kontakt;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "licne_informacije")
@XmlType(name = "TLicneInformacije", propOrder = {"drzavljanstvo", "jmbg", "kontakt", "imePrezime", "davalacKrvi"})
public class LicneInformacije {

    @XmlElement(name = "drzavljanstvo", required = true)
    private String drzavljanstvo;

    @XmlElement(name = "jmbg", required = true)
    private String jmbg;

    @XmlElement(name = "kontakt", required = true)
    private Kontakt kontakt;

    @XmlElement(name = "puno_ime", required = true)
    private ImePrezime imePrezime;

    @XmlElement(name = "davalac_krvi", required = true)
    private Boolean davalacKrvi;


    public LicneInformacije() {
    }

    public LicneInformacije(String drzavljanstvo, String jmbg, Kontakt kontakt, ImePrezime imePrezime,
                            Boolean davalacKrvi) {
        this.drzavljanstvo = drzavljanstvo;
        this.jmbg = jmbg;
        this.kontakt = kontakt;
        this.imePrezime = imePrezime;
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

    public ImePrezime getPunoIme() {
        return imePrezime;
    }

    public void setPunoIme(ImePrezime imePrezime) {
        this.imePrezime = imePrezime;
    }

    public Boolean getDavalacKrvi() {
        return davalacKrvi;
    }

    public void setDavalacKrvi(Boolean davalacKrvi) {
        this.davalacKrvi = davalacKrvi;
    }

}
