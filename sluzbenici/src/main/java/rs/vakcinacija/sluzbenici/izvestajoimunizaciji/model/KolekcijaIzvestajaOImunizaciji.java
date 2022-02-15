package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.model.KolekcijaVakcinacionihPunktova;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.model.VakcinacioniPunkt;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "izvestaji_o_imunizaciji")
@XmlAccessorType(XmlAccessType.FIELD)
public class KolekcijaIzvestajaOImunizaciji {

    @XmlElement(name = "izvestaj_o_imunizaciji")
    private List<IzvestajOImunizaciji> izvestajiOImunizaciji;

    public KolekcijaIzvestajaOImunizaciji() {
    }

    public KolekcijaIzvestajaOImunizaciji(List<IzvestajOImunizaciji> izvestajiOImunizaciji) {
        this.izvestajiOImunizaciji = izvestajiOImunizaciji;
    }

    public static KolekcijaIzvestajaOImunizaciji of(List<IzvestajOImunizaciji> kolekcijaIzvestajaOImunizaciji) {
        return new KolekcijaIzvestajaOImunizaciji(kolekcijaIzvestajaOImunizaciji);
    }
}
