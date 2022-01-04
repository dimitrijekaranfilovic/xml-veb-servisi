package rs.vakcinacija.imunizacija.saglasnost.model;

import rs.vakcinacija.imunizacija.zajednicko.model.Kontakt;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "licne_informacije")
@XmlType(name = "TLicneInformacije",
        propOrder = {"drzavljanstvo", "punoIme", "adresa", "kontakt", "radniStatus", "zanimanjeZaposlenog"})
public class LicneInformacije {

    @XmlElement(name = "drzavljanstvo", required = true)
    private Drzavljanstvo drzavljanstvo;

    @XmlElement(name = "puno_ime", required = true)
    private PunoImePacijent punoIme;

    @XmlElement(name = "adresa", required = true)
    private Adresa adresa;

    @XmlElement(name = "kontakt", required = true)
    private Kontakt kontakt;

    @XmlElement(name = "radni_status", required = true)
    private String radniStatus;

    @XmlElement(name = "zanimanje_zaposlenog", required = true)
    private String zanimanjeZaposlenog;

    public LicneInformacije() {
    }

    public LicneInformacije(Drzavljanstvo drzavljanstvo, PunoImePacijent punoIme, Adresa adresa, Kontakt kontakt, String radniStatus, String zanimanjeZaposlenog) {
        this.drzavljanstvo = drzavljanstvo;
        this.punoIme = punoIme;
        this.adresa = adresa;
        this.kontakt = kontakt;
        this.radniStatus = radniStatus;
        this.zanimanjeZaposlenog = zanimanjeZaposlenog;
    }

    public Drzavljanstvo getDrzavljanstvo() {
        return drzavljanstvo;
    }

    public void setDrzavljanstvo(Drzavljanstvo drzavljanstvo) {
        this.drzavljanstvo = drzavljanstvo;
    }

    public PunoImePacijent getPunoIme() {
        return punoIme;
    }

    public void setPunoIme(PunoImePacijent punoIme) {
        this.punoIme = punoIme;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public Kontakt getKontakt() {
        return kontakt;
    }

    public void setKontakt(Kontakt kontakt) {
        this.kontakt = kontakt;
    }

    public String getRadniStatus() {
        return radniStatus;
    }

    public void setRadniStatus(String radniStatus) {
        this.radniStatus = radniStatus;
    }

    public String getZanimanjeZaposlenog() {
        return zanimanjeZaposlenog;
    }

    public void setZanimanjeZaposlenog(String zanimanjeZaposlenog) {
        this.zanimanjeZaposlenog = zanimanjeZaposlenog;
    }
}