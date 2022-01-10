package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "adresa")
@XmlType(name = "TAdresa", propOrder = {"ulica", "brojZgrade", "mesto", "opstina"})
@Getter
@Setter
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
}
