package rs.vakcinacija.sluzbenici.digitalnisertifikat.model;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "licne_informacije")
@XmlType(name = "TLicne_informacije", propOrder = {"ime", "prezime", "jmbg", "datumRodjenja", "brojPasosa"})
public class LicneInformacije {

    @XmlElement(name = "ime", required = true, namespace = "https://www.vakcinacija.rs/zajednicko")
    private String ime;

    @XmlElement(name = "prezime", required = true, namespace = "https://www.vakcinacija.rs/zajednicko")
    private String prezime;

    @XmlElement(name = "jmbg", required = true, namespace = "https://www.vakcinacija.rs/zajednicko")
    private String jmbg;

    @XmlElement(name = "datum_rodjenja", required = true)
    @XmlSchemaType(name = "date")
    private Date datumRodjenja;

    @XmlElement(name = "broj_pasosa", required = true)
    private String brojPasosa;

    public LicneInformacije() {
    }

    public LicneInformacije(String ime, String prezime, String jmbg, Date datumRodjenja, String brojPasosa) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.datumRodjenja = datumRodjenja;
        this.brojPasosa = brojPasosa;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
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
