package rs.vakcinacija.sluzbenici.digitalnisertifikat.model;

import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "digitalni_potpis")
@XmlType(name = "TDigitalni_potpis", propOrder = {"drzava", "datum"})
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

    public RDFField<String> getDrzava() {
        return drzava;
    }

    public void setDrzava(RDFField<String> drzava) {
        this.drzava = drzava;
    }

    public RDFField<Date> getDatum() {
        return datum;
    }

    public void setDatum(RDFField<Date> datum) {
        this.datum = datum;
    }
}
