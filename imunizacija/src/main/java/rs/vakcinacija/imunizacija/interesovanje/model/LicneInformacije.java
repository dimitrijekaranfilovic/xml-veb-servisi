package rs.vakcinacija.imunizacija.interesovanje.model;


import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.ImePrezime;
import rs.vakcinacija.zajednicko.model.Kontakt;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "licne_informacije")
@XmlType(name = "TLicneInformacije", propOrder = {"drzavljanstvo", "jmbg", "kontakt", "imePrezime", "davalacKrvi"})
@Getter
@Setter
public class LicneInformacije {

    @XmlElement(name = "drzavljanstvo", required = true)
    private RDFField<String> drzavljanstvo;

    @XmlElement(name = "jmbg", required = true)
    private RDFField<String> jmbg;

    @XmlElement(name = "kontakt", required = true)
    private RDFField<Kontakt> kontakt;

    @XmlElement(name = "puno_ime", required = true)
    private RDFField<ImePrezime> imePrezime;

    @XmlElement(name = "davalac_krvi", required = true)
    private RDFField<Boolean> davalacKrvi;


    public LicneInformacije() {
    }

    public LicneInformacije(RDFField<String> drzavljanstvo, RDFField<String> jmbg,
                            RDFField<Kontakt> kontakt, RDFField<ImePrezime> imePrezime, RDFField<Boolean> davalacKrvi) {
        this.drzavljanstvo = drzavljanstvo;
        this.jmbg = jmbg;
        this.kontakt = kontakt;
        this.imePrezime = imePrezime;
        this.davalacKrvi = davalacKrvi;
    }
}
