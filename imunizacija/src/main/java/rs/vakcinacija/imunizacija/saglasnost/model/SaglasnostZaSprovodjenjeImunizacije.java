package rs.vakcinacija.imunizacija.saglasnost.model;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "saglasnost_za_sprovodjene_preporucene_imunizacije")
public class SaglasnostZaSprovodjenjeImunizacije {

    @XmlElement(name = "datum", required = true)
    @XmlSchemaType(name = "date")
    private Date datum;

    @XmlElement(name = "pacijent", required = true)
    private Pacijent pacijent;

    private Vakcinacija vakcinacija;

    public SaglasnostZaSprovodjenjeImunizacije() {}

    public SaglasnostZaSprovodjenjeImunizacije(Date datum, Pacijent pacijent, Vakcinacija vakcinacija) {
        this.datum = datum;
        this.pacijent = pacijent;
        this.vakcinacija = vakcinacija;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public Vakcinacija getVakcinacija() {
        return vakcinacija;
    }

    public void setVakcinacija(Vakcinacija vakcinacija) {
        this.vakcinacija = vakcinacija;
    }
}
