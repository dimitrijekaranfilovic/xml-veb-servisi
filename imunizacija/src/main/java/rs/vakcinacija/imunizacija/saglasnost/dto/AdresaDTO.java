package rs.vakcinacija.imunizacija.saglasnost.dto;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "adresa")
@XmlType(name = "TAdresa", propOrder = {"ulica", "brojZgrade", "mesto", "opstina"})
@Getter
@Setter
public class AdresaDTO {

    @XmlElement(name = "ulica", required = true)
    private RDFString ulica;

    @XmlElement(name = "brojZgrade", required = true)
    private RDFString brojZgrade;

    @XmlElement(name = "mesto", required = true)
    private RDFString mesto;

    @XmlElement(name = "opstina", required = true)
    private RDFString opstina;

    public AdresaDTO() {}

    public AdresaDTO(RDFString ulica, RDFString brojZgrade, RDFString mesto, RDFString opstina) {
        this.ulica = ulica;
        this.brojZgrade = brojZgrade;
        this.mesto = mesto;
        this.opstina = opstina;
    }
}
