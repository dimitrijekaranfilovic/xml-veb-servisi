package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "saglasnost")
@XmlType(name = "TSaglasnost", propOrder = {"izjava", "nazivImunoloskogLeka"})
@Getter
@Setter
public class Saglasnost {

    @XmlElement(name = "izjava", required = true)
    private RDFField<Boolean> izjava;

    @XmlElement(name = "naziv_imunoloskog_leka", required = true)
    private RDFField<String> nazivImunoloskogLeka;

    public Saglasnost() {
    }

    public Saglasnost(RDFField<Boolean> izjava, RDFField<String> nazivImunoloskogLeka) {
        this.izjava = izjava;
        this.nazivImunoloskogLeka = nazivImunoloskogLeka;
    }
}
