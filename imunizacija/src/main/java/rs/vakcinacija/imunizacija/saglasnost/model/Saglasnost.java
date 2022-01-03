package rs.vakcinacija.imunizacija.saglasnost.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "saglasnost")
@XmlType(name = "TSaglasnost", propOrder = {"izjava", "nazivImunoloskogLeka"})
public class Saglasnost {

    @XmlElement(name = "izjava", required = true)
    private Boolean izjava;

    @XmlElement(name = "naziv_imunoloskog_leka", required = true)
    private String nazivImunoloskogLeka;

    public Saglasnost() {}

    public Saglasnost(Boolean izjava, String nazivImunoloskogLeka) {
        this.izjava = izjava;
        this.nazivImunoloskogLeka = nazivImunoloskogLeka;
    }

    public Boolean getIzjava() {
        return izjava;
    }

    public void setIzjava(Boolean izjava) {
        this.izjava = izjava;
    }

    public String getNazivImunoloskogLeka() {
        return nazivImunoloskogLeka;
    }

    public void setNazivImunoloskogLeka(String nazivImunoloskogLeka) {
        this.nazivImunoloskogLeka = nazivImunoloskogLeka;
    }
}
