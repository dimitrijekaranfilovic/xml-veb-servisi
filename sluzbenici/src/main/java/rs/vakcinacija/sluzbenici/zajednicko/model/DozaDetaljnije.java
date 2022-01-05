package rs.vakcinacija.sluzbenici.zajednicko.model;

import rs.vakcinacija.sluzbenici.digitalnisertifikat.model.DozaVakcine;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "doza_detaljnije")
@XmlType(name = "TDoza_detaljnije", propOrder = {"tip", "proizvodjac"})
public class DozaDetaljnije extends Doza {

    @XmlElement(name = "tip", required = true)
    private String tip;

    @XmlElement(name = "proizvodjac", required = true)
    private String proizvodjac;

    public DozaDetaljnije() {
    }

    public DozaDetaljnije(Integer brojDoze, Date datumDavanja, String brojSerije, String tip, String proizvodjac) {
        super(brojDoze, datumDavanja, brojSerije);
        this.tip = tip;
        this.proizvodjac = proizvodjac;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }
}
