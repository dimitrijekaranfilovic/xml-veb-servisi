package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.Kontakt;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.model.RDFField;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "licne_informacije")
@XmlType(name = "TLicneInformacije",
        propOrder = {"drzavljanstvo", "punoIme", "datumRodjenja", "mestoRodjenja", "pol", "adresa", "kontakt", "radniStatus", "zanimanjeZaposlenog", "socijalnaZastita"})
@Getter
@Setter
public class LicneInformacije extends RDFField {

    @XmlElement(name = "drzavljanstvo", required = true)
    private Drzavljanstvo drzavljanstvo;

    @XmlElement(name = "puno_ime", required = true)
    private PunoImePacijent punoIme;

    @XmlElement(name = "datum_rodjenja", required = true)
    private RDFDate datumRodjenja;

    @XmlElement(name = "mesto_rodjenja", required = true)
    private RDFString mestoRodjenja;

    @XmlElement(name = "pol", required = true)
    private RDFString pol;

    @XmlElement(name = "adresa", required = true)
    private Adresa adresa;

    @XmlElement(name = "kontakt", required = true)
    private Kontakt kontakt;

    @XmlElement(name = "radni_status", required = true)
    private RDFString radniStatus;

    @XmlElement(name = "zanimanje_zaposlenog", required = true)
    private RDFString zanimanjeZaposlenog;

    @XmlElement(name = "socijalna_zastita", required = true)
    private SocijalnaZastita socijalnaZastita;

    public LicneInformacije() {
    }

    public LicneInformacije(Drzavljanstvo drzavljanstvo, PunoImePacijent punoIme, RDFDate datumRodjenja,
                            RDFString mestoRodjenja, RDFString pol, Adresa adresa, Kontakt kontakt,
                            RDFString radniStatus, RDFString zanimanjeZaposlenog, SocijalnaZastita zastita) {
        this.drzavljanstvo = drzavljanstvo;
        this.punoIme = punoIme;
        this.adresa = adresa;
        this.kontakt = kontakt;
        this.radniStatus = radniStatus;
        this.zanimanjeZaposlenog = zanimanjeZaposlenog;
        this.socijalnaZastita = zastita;
        this.datumRodjenja = datumRodjenja;
        this.mestoRodjenja = mestoRodjenja;
        this.pol = pol;
    }
}
