package rs.vakcinacija.sluzbenici.digitalnisertifikat.model;


import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.LicniPodaci;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "licne_informacije")
@XmlType(name = "TLicne_informacije", propOrder = {"datumRodjenja", "brojPasosa"})
@Getter
@Setter
public class LicneInformacije extends LicniPodaci {

    @XmlElement(name = "datum_rodjenja", required = true)
    private RDFField<Date> datumRodjenja;

    @XmlElement(name = "broj_pasosa", required = true)
    private RDFField<String> brojPasosa;

    public LicneInformacije() {
    }

    public LicneInformacije(RDFField<String> ime, RDFField<String> prezime, RDFField<String> jmbg, RDFField<String> pol, RDFField<Date> datumRodjenja, RDFField<String> brojPasosa) {
        super(ime, prezime, jmbg, pol);
        this.datumRodjenja = datumRodjenja;
        this.brojPasosa = brojPasosa;
    }
}
