package rs.vakcinacija.sluzbenici.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFBoolean;
import rs.vakcinacija.zajednicko.model.RDFField;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "saglasnost")
@XmlType(name = "TSaglasnost", propOrder = {"izjava", "nazivImunoloskogLeka"})
@Getter
@Setter
public class Saglasnost extends RDFField {

    @XmlElement(name = "izjava", required = true)
    private RDFBoolean izjava;

    @XmlElement(name = "naziv_imunoloskog_leka", required = true)
    private RDFString nazivImunoloskogLeka;

    public Saglasnost() {
    }

    public Saglasnost(RDFBoolean izjava, RDFString nazivImunoloskogLeka) {
        this.izjava = izjava;
        this.nazivImunoloskogLeka = nazivImunoloskogLeka;
    }
}
