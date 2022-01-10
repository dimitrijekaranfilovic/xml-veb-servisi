package rs.vakcinacija.imunizacija.interesovanje.model;


import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.*;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "licne_informacije")
@XmlType(name = "TLicneInformacije", propOrder = {"drzavljanstvo", "jmbg", "kontakt", "imePrezime", "davalacKrvi"})
@Getter
@Setter
public class LicneInformacije extends RDFField {

    @XmlElement(name = "drzavljanstvo", required = true)
    private RDFString drzavljanstvo;

    @XmlElement(name = "jmbg", required = true)
    private RDFString jmbg;

    @XmlElement(name = "kontakt", required = true)
    private Kontakt kontakt;

    @XmlElement(name = "puno_ime", required = true)
    private ImePrezime imePrezime;

    @XmlElement(name = "davalac_krvi", required = true)
    private RDFBoolean davalacKrvi;


    public LicneInformacije() {
    }

    public LicneInformacije(RDFString drzavljanstvo, RDFString jmbg,
                            Kontakt kontakt, ImePrezime imePrezime, RDFBoolean davalacKrvi) {
        this.drzavljanstvo = drzavljanstvo;
        this.jmbg = jmbg;
        this.kontakt = kontakt;
        this.imePrezime = imePrezime;
        this.davalacKrvi = davalacKrvi;
    }
}
