package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.Kontakt;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "licne_informacije")
@XmlType(name = "TLicneInformacije",
        propOrder = {"drzavljanstvo", "punoIme", "adresa", "kontakt", "radniStatus", "zanimanjeZaposlenog"})
@Getter
@Setter
public class LicneInformacije {

    @XmlElement(name = "drzavljanstvo", required = true)
    private RDFField<Drzavljanstvo> drzavljanstvo;

    @XmlElement(name = "puno_ime", required = true)
    private RDFField<PunoImePacijent> punoIme;

    @XmlElement(name = "adresa", required = true)
    private RDFField<Adresa> adresa;

    @XmlElement(name = "kontakt", required = true)
    private RDFField<Kontakt> kontakt;

    @XmlElement(name = "radni_status", required = true)
    private RDFField<String> radniStatus;

    @XmlElement(name = "zanimanje_zaposlenog", required = true)
    private RDFField<String> zanimanjeZaposlenog;

    public LicneInformacije() {
    }

    public LicneInformacije(RDFField<Drzavljanstvo> drzavljanstvo, RDFField<PunoImePacijent> punoIme,
                            RDFField<Adresa> adresa, RDFField<Kontakt> kontakt,
                            RDFField<String> radniStatus, RDFField<String> zanimanjeZaposlenog) {
        this.drzavljanstvo = drzavljanstvo;
        this.punoIme = punoIme;
        this.adresa = adresa;
        this.kontakt = kontakt;
        this.radniStatus = radniStatus;
        this.zanimanjeZaposlenog = zanimanjeZaposlenog;
    }
}
