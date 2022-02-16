package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.BaseDocument;
import rs.vakcinacija.zajednicko.model.RDFDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "saglasnost_za_sprovodjene_preporucene_imunizacije")
@Getter
@Setter
public class SaglasnostZaSprovodjenjeImunizacije extends BaseDocument {

    @XmlElement(name = "datum", required = true)
    private RDFDate datum;

    @XmlElement(name = "pacijent", required = true)
    private Pacijent pacijent;

    private Vakcinacija vakcinacija;

    public SaglasnostZaSprovodjenjeImunizacije() {
    }

    public SaglasnostZaSprovodjenjeImunizacije(RDFDate datum, Pacijent pacijent, Vakcinacija vakcinacija) {
        this.datum = datum;
        this.pacijent = pacijent;
        this.vakcinacija = vakcinacija;
    }

    @Override
    public String provideEmail() {
        return pacijent.getLicneInformacije().getKontakt().getEmail().getValue();
    }
}
