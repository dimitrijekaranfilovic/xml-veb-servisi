package rs.vakcinacija.sluzbenici.digitalnisertifikat.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.model.RDFField;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "digitalni_sertifikat")
@Getter
@Setter
public class DigitalniSertifikat extends RDFField {

    @XmlElement(name = "broj_sertifikata", required = true)
    private RDFString brojSertifikata;

    @XmlElement(name = "datum_vreme_izdavanja", required = true)
    private RDFDate datumVremeIzdavanja;

    @XmlElement(name = "licne_informacije", required = true)
    private LicneInformacije licneInformacije;

    @XmlElement(name = "vakcinacija", required = true)
    private Vakcinacija vakcinacija;

    @XmlElement(name = "testovi", required = true)
    private Testovi testovi;

    @XmlElement(name = "informacije_o_sertifikatu", required = true)
    private InformacijeOSertifikatu informacijeOSertifikatu;

    public DigitalniSertifikat() {
    }

    public DigitalniSertifikat(RDFString brojSertifikata, RDFDate datumVremeIzdavanja,
                               LicneInformacije licneInformacije, Vakcinacija vakcinacija,
                               Testovi testovi, InformacijeOSertifikatu informacijeOSertifikatu) {
        this.brojSertifikata = brojSertifikata;
        this.datumVremeIzdavanja = datumVremeIzdavanja;
        this.licneInformacije = licneInformacije;
        this.vakcinacija = vakcinacija;
        this.testovi = testovi;
        this.informacijeOSertifikatu = informacijeOSertifikatu;
    }
}
