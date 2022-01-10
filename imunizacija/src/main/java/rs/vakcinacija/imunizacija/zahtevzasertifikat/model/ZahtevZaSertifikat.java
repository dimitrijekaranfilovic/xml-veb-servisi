package rs.vakcinacija.imunizacija.zahtevzasertifikat.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "zahtev_za_sertifikat")
@Getter
@Setter
public class ZahtevZaSertifikat {

    @XmlElement(name = "mesto", required = true)
    private String mesto;

    @XmlElement(name = "datum", required = true)
    private Date datum;

    @XmlElement(name = "podnosilac_zahteva", required = true)
    private PodnosilacZahteva podnosilacZahteva;

    @XmlElement(name = "razlog_za_podnosenje_zahteva", required = true)
    private String razlogZaPodnosenjeZahteva;


    public ZahtevZaSertifikat() {
    }

    public ZahtevZaSertifikat(String mesto, Date datum, PodnosilacZahteva podnosilacZahteva, String razlogZaPodnosenjeZahteva) {
        this.mesto = mesto;
        this.datum = datum;
        this.podnosilacZahteva = podnosilacZahteva;
        this.razlogZaPodnosenjeZahteva = razlogZaPodnosenjeZahteva;
    }
}
