package rs.vakcinacija.imunizacija.saglasnost.dto;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "licneInformacije")
@XmlType(name = "TLicneInformacije",
        propOrder = {"drzavljanstvo", "punoIme", "datumRodjenja", "mestoRodjenja", "pol", "adresa", "kontakt",
                "radniStatus", "zanimanjeZaposlenog", "socijalnaZastita"})
@Getter
@Setter
public class LicneInformacijeDTO {

    @XmlElement(name = "drzavljanstvo", required = true)
    private DrzavljanstvoDTO drzavljanstvo;

    @XmlElement(name = "punoIme", required = true)
    private PunoImePacijentDTO punoIme;

    @XmlElement(name = "datumRodjenja", required = true)
    private RDFDate datumRodjenja;

    @XmlElement(name = "mestoRodjenja", required = true)
    private RDFString mestoRodjenja;

    @XmlElement(name = "pol", required = true)
    private RDFString pol;

    @XmlElement(name = "adresa", required = true)
    private AdresaDTO adresa;

    @XmlElement(name = "kontakt", required = true)
    private KontaktDTO kontakt;

    @XmlElement(name = "radniStatus", required = true)
    private RDFString radniStatus;

    @XmlElement(name = "zanimanjeZaposlenog", required = true)
    private RDFString zanimanjeZaposlenog;

    @XmlElement(name = "socijalnaZastita", required = true)
    private SocijalnaZastitaDTO socijalnaZastita;

    public LicneInformacijeDTO() {}

    public LicneInformacijeDTO(DrzavljanstvoDTO drzavljanstvo, PunoImePacijentDTO punoIme, RDFDate datumRodjenja,
                               RDFString mestoRodjenja, RDFString pol, AdresaDTO adresa, KontaktDTO kontakt,
                               RDFString radniStatus, RDFString zanimanjeZaposlenog,
                               SocijalnaZastitaDTO socijalnaZastita) {
        this.drzavljanstvo = drzavljanstvo;
        this.punoIme = punoIme;
        this.datumRodjenja = datumRodjenja;
        this.mestoRodjenja = mestoRodjenja;
        this.pol = pol;
        this.adresa = adresa;
        this.kontakt = kontakt;
        this.radniStatus = radniStatus;
        this.zanimanjeZaposlenog = zanimanjeZaposlenog;
        this.socijalnaZastita = socijalnaZastita;
    }
}
