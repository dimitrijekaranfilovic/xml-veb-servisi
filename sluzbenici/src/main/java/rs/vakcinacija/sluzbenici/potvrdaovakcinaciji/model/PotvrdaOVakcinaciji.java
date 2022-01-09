package rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model;


import rs.vakcinacija.zajednicko.model.LicniPodaci;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "potvrda_o_vakcinaciji")
public class PotvrdaOVakcinaciji {


    @XmlElement(name = "sifra_potvrde", required = true)
    private RDFField<String> sifraPotvrde;

    @XmlElement(name = "datum_izdavanja", required = true)
    private RDFField<Date> datumIzdavanja;

    @XmlElement(name = "qr_kod", required = true)
    private RDFField<String> qrKod;

    @XmlElement(name = "licne_informacije", required = true)
    private RDFField<LicniPodaci> licneInformacije;

    @XmlElement(name = "vakcinacija", required = true)
    private RDFField<Vakcinacija> vakcinacija;

    public PotvrdaOVakcinaciji(){
        super();
    }

    public PotvrdaOVakcinaciji(RDFField<String> sifraPotvrde,
                               RDFField<Date> datumIzdavanja,
                               RDFField<String> qrKod,
                               RDFField<LicniPodaci> licneInformacije,
                               RDFField<Vakcinacija> vakcinacija) {
        this.sifraPotvrde = sifraPotvrde;
        this.datumIzdavanja = datumIzdavanja;
        this.qrKod = qrKod;
        this.licneInformacije = licneInformacije;
        this.vakcinacija = vakcinacija;
    }

    public RDFField<String> getSifraPotvrde() {
        return sifraPotvrde;
    }

    public void setSifraPotvrde(RDFField<String> sifraPotvrde) {
        this.sifraPotvrde = sifraPotvrde;
    }

    public RDFField<Date> getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(RDFField<Date> datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public RDFField<String> getQrKod() {
        return qrKod;
    }

    public void setQrKod(RDFField<String> qrKod) {
        this.qrKod = qrKod;
    }

    public RDFField<LicniPodaci> getLicneInformacije() {
        return licneInformacije;
    }

    public void setLicneInformacije(RDFField<LicniPodaci> licneInformacije) {
        this.licneInformacije = licneInformacije;
    }

    public RDFField<Vakcinacija> getVakcinacija() {
        return vakcinacija;
    }

    public void setVakcinacija(RDFField<Vakcinacija> vakcinacija) {
        this.vakcinacija = vakcinacija;
    }
}
