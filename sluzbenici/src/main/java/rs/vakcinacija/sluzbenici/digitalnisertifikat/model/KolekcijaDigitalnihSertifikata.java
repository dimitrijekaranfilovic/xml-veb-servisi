package rs.vakcinacija.sluzbenici.digitalnisertifikat.model;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter @Setter
@XmlRootElement(name = "digitalni_sertifikati")
@XmlAccessorType(XmlAccessType.FIELD)
public class KolekcijaDigitalnihSertifikata {

    @XmlElement(name = "digitalni_sertifikat")
    private List<DigitalniSertifikat> digitalniSertifikati;

    public KolekcijaDigitalnihSertifikata() {
    }

    public KolekcijaDigitalnihSertifikata(List<DigitalniSertifikat> digitalniSertifikati) {
        this.digitalniSertifikati = digitalniSertifikati;
    }

    public static KolekcijaDigitalnihSertifikata of(List<DigitalniSertifikat> digitalniSertifikati) {
        return new KolekcijaDigitalnihSertifikata(digitalniSertifikati);
    }
}
