package rs.vakcinacija.imunizacija.digitalnisertifikat.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.model.RDFField;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "digitalni_potpis")
@XmlType(name = "TDigitalni_potpis", propOrder = {"drzava", "datum"})
@Getter
@Setter
public class DigitalniPotpis extends RDFField {

    @XmlElement(name = "drzava", required = true)
    private RDFString drzava;

    @XmlElement(name = "datum", required = true)
    private RDFDate datum;

    public DigitalniPotpis() {
    }

    public DigitalniPotpis(RDFString drzava, RDFDate datum) {
        this.drzava = drzava;
        this.datum = datum;
    }
}
