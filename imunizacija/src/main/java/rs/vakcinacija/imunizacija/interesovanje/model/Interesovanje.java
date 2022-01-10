package rs.vakcinacija.imunizacija.interesovanje.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "interesovanje")
@Getter
@Setter
public class Interesovanje {

    @XmlElement(name = "datum", required = true)
    @XmlSchemaType(name = "date")
    private Date datum;

    @XmlElement(name = "licne_informacije", required = true)
    private LicneInformacije licneInformacije;

    @XmlElement(name = "odabrana_lokacija_primanja_vakcine", required = true)
    private String odabranaLokacijaPrimanjaVakcine;

    @XmlElement(name = "odabrani_proizvodjaci", required = true)
    private OdabraniProizvodjaci odabraniProizvodjaci;

    public Interesovanje() {
    }

    public Interesovanje(Date datum, LicneInformacije licneInformacije,
                         String odabranaLokacijaPrimanjaVakcine,
                         OdabraniProizvodjaci odabraniProizvodjaci) {
        this.datum = datum;
        this.licneInformacije = licneInformacije;
        this.odabranaLokacijaPrimanjaVakcine = odabranaLokacijaPrimanjaVakcine;
        this.odabraniProizvodjaci = odabraniProizvodjaci;
    }
}
