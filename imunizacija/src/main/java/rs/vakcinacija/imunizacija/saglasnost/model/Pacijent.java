package rs.vakcinacija.imunizacija.saglasnost.model;

import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "pacijent")
@XmlType(name = "TPacijent", propOrder = {"licneInformacije", "saglasnost"})
public class Pacijent {

    @XmlElement(name = "licne_informacije", required = true)
    private RDFField<LicneInformacije> licneInformacije;

    @XmlElement(name = "saglasnost", required = true)
    private RDFField<Saglasnost> saglasnost;

    public Pacijent() {}

    public Pacijent(RDFField<LicneInformacije> licneInformacije, RDFField<Saglasnost> saglasnost) {
        this.licneInformacije = licneInformacije;
        this.saglasnost = saglasnost;
    }

    public RDFField<LicneInformacije> getLicneInformacije() {
        return licneInformacije;
    }

    public void setLicneInformacije(RDFField<LicneInformacije> licneInformacije) {
        this.licneInformacije = licneInformacije;
    }

    public RDFField<Saglasnost> getSaglasnost() {
        return saglasnost;
    }

    public void setSaglasnost(RDFField<Saglasnost> saglasnost) {
        this.saglasnost = saglasnost;
    }
}
