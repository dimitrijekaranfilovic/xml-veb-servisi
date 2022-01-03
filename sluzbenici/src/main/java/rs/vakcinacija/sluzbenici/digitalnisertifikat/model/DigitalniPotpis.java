package rs.vakcinacija.sluzbenici.digitalnisertifikat.model;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "digitalni_potpis")
@XmlType(name = "TDigitalni_potpis", propOrder = {"drzava", "datum"})
public class DigitalniPotpis {

    @XmlElement(name = "drzava", required = true)
    private String drzava;

    @XmlElement(name = "datum", required = true)
    @XmlSchemaType(name = "date")
    private Date datum;

    public DigitalniPotpis() {
    }

    public DigitalniPotpis(String drzava, Date datum) {
        this.drzava = drzava;
        this.datum = datum;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
}
