package rs.vakcinacija.sluzbenici.digitalnisertifikat.model;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "digitalni_sertifikat")
public class DigitalniSertifikat {

    @XmlElement(name = "broj_sertifikata", required = true)
    private String brojSertifikata;

    @XmlElement(name = "darum_vreme_izdavanja", required = true)
    @XmlSchemaType(name = "date")
    private Date datumVremeIzdavanja;

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

    public DigitalniSertifikat(String brojSertifikata, Date datumVremeIzdavanja, LicneInformacije licneInformacije,
                               Vakcinacija vakcinacija, Testovi testovi, InformacijeOSertifikatu informacijeOSertifikatu) {
        this.brojSertifikata = brojSertifikata;
        this.datumVremeIzdavanja = datumVremeIzdavanja;
        this.licneInformacije = licneInformacije;
        this.vakcinacija = vakcinacija;
        this.testovi = testovi;
        this.informacijeOSertifikatu = informacijeOSertifikatu;
    }
}
