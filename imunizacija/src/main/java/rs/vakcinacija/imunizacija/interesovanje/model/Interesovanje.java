package rs.vakcinacija.imunizacija.interesovanje.model;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "interesovanje")
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

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public LicneInformacije getLicneInformacije() {
        return licneInformacije;
    }

    public void setLicneInformacije(LicneInformacije licneInformacije) {
        this.licneInformacije = licneInformacije;
    }

    public String getOdabranaLokacijaPrimanjaVakcine() {
        return odabranaLokacijaPrimanjaVakcine;
    }

    public void setOdabranaLokacijaPrimanjaVakcine(String odabranaLokacijaPrimanjaVakcine) {
        this.odabranaLokacijaPrimanjaVakcine = odabranaLokacijaPrimanjaVakcine;
    }

    public OdabraniProizvodjaci getOdabraniProizvodjaci() {
        return odabraniProizvodjaci;
    }

    public void setOdabraniProizvodjaci(OdabraniProizvodjaci odabraniProizvodjaci) {
        this.odabraniProizvodjaci = odabraniProizvodjaci;
    }
}
