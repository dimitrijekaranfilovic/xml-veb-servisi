package rs.vakcinacija.imunizacija.interesovanje.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.BaseDocument;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "interesovanje")
@Getter
@Setter
public class Interesovanje extends BaseDocument {

    @XmlElement(name = "datum", required = true)
    private RDFDate datum;

    @XmlElement(name = "licne_informacije", required = true)
    private LicneInformacije licneInformacije;

    @XmlElement(name = "odabrana_lokacija_primanja_vakcine", required = true)
    private RDFString odabranaLokacijaPrimanjaVakcine;

    @XmlElement(name = "odabrani_proizvodjaci", required = true)
    private OdabraniProizvodjaci odabraniProizvodjaci;

    @XmlElement(name = "datum_termina", required = false)
    private RDFDate datumTermina;

    public Interesovanje() {
    }

    public Interesovanje(RDFDate datum, LicneInformacije licneInformacije, RDFString odabranaLokacijaPrimanjaVakcine,
                         OdabraniProizvodjaci odabraniProizvodjaci, RDFDate datumTermina) {
        this.datum = datum;
        this.licneInformacije = licneInformacije;
        this.odabranaLokacijaPrimanjaVakcine = odabranaLokacijaPrimanjaVakcine;
        this.odabraniProizvodjaci = odabraniProizvodjaci;
        this.datumTermina = datumTermina;
    }
}
