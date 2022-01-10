package rs.vakcinacija.sluzbenici.digitalnisertifikat.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "digitalni_potpis")
@XmlType(name = "TDigitalni_potpis", propOrder = {"drzava", "datum"})
@Getter
@Setter
public class DigitalniPotpis {

    @XmlElement(name = "drzava", required = true)
    private RDFField<String> drzava;

    @XmlElement(name = "datum", required = true)
    private RDFField<Date> datum;

    public DigitalniPotpis() {
    }

    public DigitalniPotpis(RDFField<String> drzava, RDFField<Date> datum) {
        this.drzava = drzava;
        this.datum = datum;
    }
}
