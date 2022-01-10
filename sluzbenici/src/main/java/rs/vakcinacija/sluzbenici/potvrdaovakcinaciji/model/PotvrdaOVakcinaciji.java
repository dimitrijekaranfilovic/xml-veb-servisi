package rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model;


import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.LicniPodaci;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "potvrda_o_vakcinaciji")
@Getter
@Setter
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
}
