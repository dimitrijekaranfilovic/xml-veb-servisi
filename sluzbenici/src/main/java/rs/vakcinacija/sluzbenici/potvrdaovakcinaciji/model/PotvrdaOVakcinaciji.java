package rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model;


import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "potvrda_o_vakcinaciji")
@Getter
@Setter
public class PotvrdaOVakcinaciji extends BaseDocument {


    @XmlElement(name = "sifra_potvrde", required = true)
    private RDFString sifraPotvrde;

    @XmlElement(name = "datum_izdavanja", required = true)
    private RDFDate datumIzdavanja;

    @XmlElement(name = "qr_kod", required = true)
    private RDFString qrKod;

    @XmlElement(name = "licne_informacije", required = true)
    private LicneInformacije licneInformacije;

    @XmlElement(name = "email", required = true)
    private RDFString email;

    @XmlElement(name = "vakcinacija", required = true)
    private Vakcinacija vakcinacija;

    public PotvrdaOVakcinaciji(){
        super();
    }

    public PotvrdaOVakcinaciji(RDFString sifraPotvrde,
                               RDFDate datumIzdavanja,
                               RDFString qrKod,
                               LicneInformacije licneInformacije,
                               Vakcinacija vakcinacija) {
        this.sifraPotvrde = sifraPotvrde;
        this.datumIzdavanja = datumIzdavanja;
        this.qrKod = qrKod;
        this.licneInformacije = licneInformacije;
        this.vakcinacija = vakcinacija;
    }

    @Override
    public String provideEmail() {
        if (email == null) {
            return "";
        }
        return email.getValue();
    }
}
