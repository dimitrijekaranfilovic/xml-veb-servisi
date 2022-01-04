package rs.vakcinacija.sluzbenici.digitalnisertifikat.model;

import rs.vakcinacija.sluzbenici.zajednicko.model.LicniPodaci;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "licne_informacije")
@XmlType(name = "TLicne_informacije", propOrder = {"datumRodjenja", "brojPasosa"})
public class LicneInformacije extends LicniPodaci {

    @XmlElement(name = "datum_rodjenja", required = true)
    @XmlSchemaType(name = "date")
    private Date datumRodjenja;

    @XmlElement(name = "broj_pasosa", required = true)
    private String brojPasosa;

    public LicneInformacije() {
    }

    public LicneInformacije(String ime, String prezime, String jmbg, String pol, Date datumRodjenja, String brojPasosa) {
        super(ime, prezime, jmbg, pol);
        this.datumRodjenja = datumRodjenja;
        this.brojPasosa = brojPasosa;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getBrojPasosa() {
        return brojPasosa;
    }

    public void setBrojPasosa(String brojPasosa) {
        this.brojPasosa = brojPasosa;
    }
}
