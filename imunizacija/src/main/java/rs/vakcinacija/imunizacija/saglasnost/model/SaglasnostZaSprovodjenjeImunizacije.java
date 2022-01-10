package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "saglasnost_za_sprovodjene_preporucene_imunizacije")
@Getter
@Setter
public class SaglasnostZaSprovodjenjeImunizacije {

    @XmlElement(name = "datum", required = true)
    private RDFField<Date> datum;

    @XmlElement(name = "pacijent", required = true)
    private RDFField<Pacijent> pacijent;

    private RDFField<Vakcinacija> vakcinacija;

    public SaglasnostZaSprovodjenjeImunizacije() {
    }

    public SaglasnostZaSprovodjenjeImunizacije(RDFField<Date> datum, RDFField<Pacijent> pacijent, RDFField<Vakcinacija> vakcinacija) {
        this.datum = datum;
        this.pacijent = pacijent;
        this.vakcinacija = vakcinacija;
    }
}
