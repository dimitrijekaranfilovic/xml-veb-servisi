package rs.vakcinacija.sluzbenici.zahtevzasertifikat.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "zahtevi_za_sertifikat")
@XmlAccessorType(XmlAccessType.FIELD)
public class KolekcijaZahtevaZaSertifikat {

    @XmlElement(name = "zahtev_za_sertifikat")
    private List<ZahtevZaSertifikat> zahteviZaSertifikat;

    public KolekcijaZahtevaZaSertifikat() {
    }

    public KolekcijaZahtevaZaSertifikat(List<ZahtevZaSertifikat> zahteviZaSertifikat) {
        this.zahteviZaSertifikat = zahteviZaSertifikat;
    }

    public static KolekcijaZahtevaZaSertifikat of(List<ZahtevZaSertifikat> zahteviZaSertifikat) {
        return new KolekcijaZahtevaZaSertifikat(zahteviZaSertifikat);
    }
}
