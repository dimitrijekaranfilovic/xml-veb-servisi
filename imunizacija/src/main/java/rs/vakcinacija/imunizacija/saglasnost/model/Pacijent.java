package rs.vakcinacija.imunizacija.saglasnost.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "pacijent")
@XmlType(name = "TPacijent", propOrder = {"licneInformacije", "saglasnost"})
public class Pacijent {

    @XmlElement(name = "licne_informacije", required = true)
    private LicneInformacije licneInformacije;

    @XmlElement(name = "saglasnost", required = true)
    private Saglasnost saglasnost;

    public Pacijent() {}

    public Pacijent(LicneInformacije licneInformacije, Saglasnost saglasnost) {
        this.licneInformacije = licneInformacije;
        this.saglasnost = saglasnost;
    }

    public LicneInformacije getLicneInformacije() {
        return licneInformacije;
    }

    public void setLicneInformacije(LicneInformacije licneInformacije) {
        this.licneInformacije = licneInformacije;
    }

    public Saglasnost getSaglasnost() {
        return saglasnost;
    }

    public void setSaglasnost(Saglasnost saglasnost) {
        this.saglasnost = saglasnost;
    }
}
