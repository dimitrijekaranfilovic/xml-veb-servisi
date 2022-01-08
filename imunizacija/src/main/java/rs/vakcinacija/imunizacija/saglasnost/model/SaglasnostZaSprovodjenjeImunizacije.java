package rs.vakcinacija.imunizacija.saglasnost.model;

import rs.vakcinacija.zajednicko.model.PropertyXMLField;
import rs.vakcinacija.zajednicko.model.PropertyXMLFieldAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "saglasnost_za_sprovodjene_preporucene_imunizacije")
public class SaglasnostZaSprovodjenjeImunizacije {

    @XmlElement(name = "datum", required = true)
    @XmlJavaTypeAdapter(PropertyXMLFieldAdapter.class)
    private PropertyXMLField<Date> datum;

    @XmlElement(name = "pacijent", required = true)
    private Pacijent pacijent;

    private Vakcinacija vakcinacija;

    public SaglasnostZaSprovodjenjeImunizacije() {}

    public SaglasnostZaSprovodjenjeImunizacije(PropertyXMLField<Date> datum, Pacijent pacijent, Vakcinacija vakcinacija) {
        this.datum = datum;
        this.pacijent = pacijent;
        this.vakcinacija = vakcinacija;
    }

    public PropertyXMLField<Date> getDatum() {
        return datum;
    }

    public void setDatum(PropertyXMLField<Date> datum) {
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
