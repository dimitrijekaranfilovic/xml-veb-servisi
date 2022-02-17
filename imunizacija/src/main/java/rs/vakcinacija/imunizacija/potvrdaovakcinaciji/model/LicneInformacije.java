package rs.vakcinacija.imunizacija.potvrdaovakcinaciji.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.LicniPodaci;
import rs.vakcinacija.zajednicko.model.RDFDate;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "licne_informacije")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TLicne_informacije")
@Getter
@Setter
public class LicneInformacije extends LicniPodaci {

    @XmlElement(name = "datum_rodjenja")
    private RDFDate datumRodjenja;


    public LicneInformacije() {
        super();
    }
}
