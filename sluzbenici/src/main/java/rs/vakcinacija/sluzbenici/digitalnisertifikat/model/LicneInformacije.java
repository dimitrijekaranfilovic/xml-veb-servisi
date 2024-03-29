package rs.vakcinacija.sluzbenici.digitalnisertifikat.model;


import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.LicniPodaci;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "licne_informacije")
@XmlType(name = "TLicne_informacije", propOrder = {"datumRodjenja", "brojPasosa", "email"})
@Getter
@Setter
public class LicneInformacije extends LicniPodaci {

    @XmlElement(name = "datum_rodjenja", required = true)
    private RDFDate datumRodjenja;

    @XmlElement(name = "broj_pasosa", required = true)
    private RDFString brojPasosa;

    @XmlElement(name = "email", required = true)
    private RDFString email;

    public LicneInformacije() {
    }

    public LicneInformacije(RDFString ime, RDFString prezime, RDFString jmbg, RDFString pol,
                            RDFDate datumRodjenja, RDFString brojPasosa, RDFString email) {
        super(ime, prezime, jmbg, pol);
        this.datumRodjenja = datumRodjenja;
        this.brojPasosa = brojPasosa;
        this.email = email;
    }
}
