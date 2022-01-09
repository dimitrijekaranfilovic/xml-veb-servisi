package rs.vakcinacija.sluzbenici.digitalnisertifikat.model;

import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "digitalni_sertifikat")
public class DigitalniSertifikat {

    @XmlElement(name = "broj_sertifikata", required = true)
    private RDFField<Date> brojSertifikata;

    @XmlElement(name = "datum_vreme_izdavanja", required = true)
    private RDFField<Date> datumVremeIzdavanja;

    @XmlElement(name = "licne_informacije", required = true)
    private RDFField<LicneInformacije> licneInformacije;

    @XmlElement(name = "vakcinacija", required = true)
    private RDFField<Vakcinacija> vakcinacija;

    @XmlElement(name = "testovi", required = true)
    private RDFField<Testovi> testovi;

    @XmlElement(name = "informacije_o_sertifikatu", required = true)
    private RDFField<InformacijeOSertifikatu> informacijeOSertifikatu;

    public DigitalniSertifikat() {
    }

    public DigitalniSertifikat(RDFField<Date> brojSertifikata, RDFField<Date> datumVremeIzdavanja,
                               RDFField<LicneInformacije> licneInformacije, RDFField<Vakcinacija> vakcinacija,
                               RDFField<Testovi> testovi, RDFField<InformacijeOSertifikatu> informacijeOSertifikatu) {
        this.brojSertifikata = brojSertifikata;
        this.datumVremeIzdavanja = datumVremeIzdavanja;
        this.licneInformacije = licneInformacije;
        this.vakcinacija = vakcinacija;
        this.testovi = testovi;
        this.informacijeOSertifikatu = informacijeOSertifikatu;
    }

    public RDFField<Date> getBrojSertifikata() {
        return brojSertifikata;
    }

    public void setBrojSertifikata(RDFField<Date> brojSertifikata) {
        this.brojSertifikata = brojSertifikata;
    }

    public RDFField<Date> getDatumVremeIzdavanja() {
        return datumVremeIzdavanja;
    }

    public void setDatumVremeIzdavanja(RDFField<Date> datumVremeIzdavanja) {
        this.datumVremeIzdavanja = datumVremeIzdavanja;
    }

    public RDFField<LicneInformacije> getLicneInformacije() {
        return licneInformacije;
    }

    public void setLicneInformacije(RDFField<LicneInformacije> licneInformacije) {
        this.licneInformacije = licneInformacije;
    }

    public RDFField<Vakcinacija> getVakcinacija() {
        return vakcinacija;
    }

    public void setVakcinacija(RDFField<Vakcinacija> vakcinacija) {
        this.vakcinacija = vakcinacija;
    }

    public RDFField<Testovi> getTestovi() {
        return testovi;
    }

    public void setTestovi(RDFField<Testovi> testovi) {
        this.testovi = testovi;
    }

    public RDFField<InformacijeOSertifikatu> getInformacijeOSertifikatu() {
        return informacijeOSertifikatu;
    }

    public void setInformacijeOSertifikatu(RDFField<InformacijeOSertifikatu> informacijeOSertifikatu) {
        this.informacijeOSertifikatu = informacijeOSertifikatu;
    }
}
