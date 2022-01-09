package rs.vakcinacija.imunizacija.saglasnost.model;

import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "saglasnost")
@XmlType(name = "TSaglasnost", propOrder = {"izjava", "nazivImunoloskogLeka"})
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

    public RDFField<Boolean> getIzjava() {
        return izjava;
    }

    public void setIzjava(RDFField<Boolean> izjava) {
        this.izjava = izjava;
    }

    public RDFField<String> getNazivImunoloskogLeka() {
        return nazivImunoloskogLeka;
    }

    public void setNazivImunoloskogLeka(RDFField<String> nazivImunoloskogLeka) {
        this.nazivImunoloskogLeka = nazivImunoloskogLeka;
    }
}
