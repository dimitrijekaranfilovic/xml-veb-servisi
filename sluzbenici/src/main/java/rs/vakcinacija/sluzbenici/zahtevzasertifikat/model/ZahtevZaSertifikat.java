package rs.vakcinacija.sluzbenici.zahtevzasertifikat.model;

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
@XmlRootElement(name = "zahtev_za_sertifikat")
@Getter
@Setter
public class ZahtevZaSertifikat extends BaseDocument {

    @XmlElement(name = "mesto", required = true)
    private RDFString mesto;

    @XmlElement(name = "datum", required = true)
    private RDFDate datum;

    @XmlElement(name = "podnosilac_zahteva", required = true)
    private PodnosilacZahteva podnosilacZahteva;

    @XmlElement(name = "razlog_za_podnosenje_zahteva", required = true)
    private RDFString razlogZaPodnosenjeZahteva;

    @XmlElement(name = "status", required = true)
    private RDFString status = RDFString.of("KREIRAN");

    @XmlElement(name = "odbijenica")
    private Odbijenica odbijenica;


    public ZahtevZaSertifikat() {
    }

    public ZahtevZaSertifikat(RDFString mesto, RDFDate datum,
                              PodnosilacZahteva podnosilacZahteva, RDFString razlogZaPodnosenjeZahteva, RDFString status, Odbijenica odbijenica) {
        this.mesto = mesto;
        this.datum = datum;
        this.podnosilacZahteva = podnosilacZahteva;
        this.razlogZaPodnosenjeZahteva = razlogZaPodnosenjeZahteva;
        this.status = status;
        this.odbijenica = odbijenica;
    }
}
