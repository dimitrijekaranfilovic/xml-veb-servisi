package rs.vakcinacija.imunizacija.interesovanje.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "interesovanje")
@Getter
@Setter
public class Interesovanje {

    @XmlElement(name = "datum", required = true)
    private RDFField<Date> datum;

    @XmlElement(name = "licne_informacije", required = true)
    private RDFField<LicneInformacije> licneInformacije;

    @XmlElement(name = "odabrana_lokacija_primanja_vakcine", required = true)
    private RDFField<String> odabranaLokacijaPrimanjaVakcine;

    @XmlElement(name = "odabrani_proizvodjaci", required = true)
    private RDFField<OdabraniProizvodjaci> odabraniProizvodjaci;

    public Interesovanje() {
    }

    public Interesovanje(RDFField<Date> datum, RDFField<LicneInformacije> licneInformacije,
                         RDFField<String> odabranaLokacijaPrimanjaVakcine,
                         RDFField<OdabraniProizvodjaci> odabraniProizvodjaci) {
        this.datum = datum;
        this.licneInformacije = licneInformacije;
        this.odabranaLokacijaPrimanjaVakcine = odabranaLokacijaPrimanjaVakcine;
        this.odabraniProizvodjaci = odabraniProizvodjaci;
    }
}
