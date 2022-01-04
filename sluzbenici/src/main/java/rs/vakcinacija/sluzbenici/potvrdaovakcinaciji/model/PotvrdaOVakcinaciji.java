package rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model;


import rs.vakcinacija.sluzbenici.zajednicko.model.LicniPodaci;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "potvrda_o_vakcinaciji")
public class PotvrdaOVakcinaciji {


    @XmlElement(name = "sifra_potvrde", required = true)
    private String sifraPotvrde;

    @XmlElement(name = "datum_izdavanja", required = true)
    @XmlSchemaType(name = "date")
    private Date datumIzdavanja;

    @XmlElement(name = "qr_kod", required = true)
    private String qrKod;

    @XmlElement(name = "licne_informacije", required = true)
    private LicniPodaci licneInformacije;

    @XmlElement(name = "vakcinacija", required = true)
    private Vakcinacija vakcinacija;

    public PotvrdaOVakcinaciji(){
        super();
    }

    public PotvrdaOVakcinaciji(String sifraPotvrde, Date datumIzdavanja, String qrKod, LicneInformacije licneInformacije) {
        this.sifraPotvrde = sifraPotvrde;
        this.datumIzdavanja = datumIzdavanja;
        this.qrKod = qrKod;
        this.licneInformacije = licneInformacije;
    }

    public String getSifraPotvrde() {
        return sifraPotvrde;
    }

    public void setSifraPotvrde(String sifraPotvrde) {
        this.sifraPotvrde = sifraPotvrde;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public String getQrKod() {
        return qrKod;
    }

    public void setQrKod(String qrKod) {
        this.qrKod = qrKod;
    }

    public LicniPodaci getLicneInformacije() {
        return licneInformacije;
    }

    public void setLicneInformacije(LicniPodaci licneInformacije) {
        this.licneInformacije = licneInformacije;
    }

    public Vakcinacija getVakcinacija() {
        return vakcinacija;
    }

    public void setVakcinacija(Vakcinacija vakcinacija) {
        this.vakcinacija = vakcinacija;
    }
}
