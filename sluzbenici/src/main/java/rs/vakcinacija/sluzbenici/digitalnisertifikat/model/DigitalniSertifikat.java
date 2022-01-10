package rs.vakcinacija.sluzbenici.digitalnisertifikat.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "digitalni_sertifikat")
@Getter
@Setter
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
}
