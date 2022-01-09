package rs.vakcinacija.imunizacija.saglasnost.model;

import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "adresa")
@XmlType(name = "TAdresa", propOrder = {"ulica", "brojZgrade", "mesto", "opstina"})
public class Adresa {

    @XmlElement(name = "ulica", required = true)
    private RDFField<String> ulica;

    @XmlElement(name = "broj_zgrade", required = true)
    private RDFField<String> brojZgrade;

    @XmlElement(name = "mesto", required = true)
    private RDFField<String> mesto;

    @XmlElement(name = "opstina", required = true)
    private RDFField<String> opstina;

    public Adresa() {}

    public Adresa(RDFField<String> ulica, RDFField<String> brojZgrade, RDFField<String> mesto, RDFField<String> opstina) {
        this.ulica = ulica;
        this.brojZgrade = brojZgrade;
        this.mesto = mesto;
        this.opstina = opstina;
    }

    public RDFField<String> getUlica() {
        return ulica;
    }

    public void setUlica(RDFField<String> ulica) {
        this.ulica = ulica;
    }

    public RDFField<String> getBrojZgrade() {
        return brojZgrade;
    }

    public void setBrojZgrade(RDFField<String> brojZgrade) {
        this.brojZgrade = brojZgrade;
    }

    public RDFField<String> getMesto() {
        return mesto;
    }

    public void setMesto(RDFField<String> mesto) {
        this.mesto = mesto;
    }

    public RDFField<String> getOpstina() {
        return opstina;
    }

    public void setOpstina(RDFField<String> opstina) {
        this.opstina = opstina;
    }
}
