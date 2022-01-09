package rs.vakcinacija.imunizacija.saglasnost.model;

import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "saglasnost_za_sprovodjene_preporucene_imunizacije")
public class SaglasnostZaSprovodjenjeImunizacije {

    @XmlElement(name = "datum", required = true)
    private RDFField<Date> datum;

    @XmlElement(name = "pacijent", required = true)
    private RDFField<Pacijent> pacijent;

    private Vakcinacija vakcinacija;

    public SaglasnostZaSprovodjenjeImunizacije() {
    }

    public SaglasnostZaSprovodjenjeImunizacije(RDFField<Date> datum, RDFField<Pacijent> pacijent, Vakcinacija vakcinacija) {
        this.datum = datum;
        this.pacijent = pacijent;
        this.vakcinacija = vakcinacija;
    }

    public RDFField<Date> getDatum() {
        return datum;
    }

    public void setDatum(RDFField<Date> datum) {
        this.datum = datum;
    }

    public RDFField<Pacijent> getPacijent() {
        return pacijent;
    }

    public void setPacijent(RDFField<Pacijent> pacijent) {
        this.pacijent = pacijent;
    }

    public Vakcinacija getVakcinacija() {
        return vakcinacija;
    }

    public void setVakcinacija(Vakcinacija vakcinacija) {
        this.vakcinacija = vakcinacija;
    }
}
