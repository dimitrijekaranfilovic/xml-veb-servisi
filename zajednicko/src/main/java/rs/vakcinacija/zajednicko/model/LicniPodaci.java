package rs.vakcinacija.zajednicko.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "licni_podaci")
@XmlType(name = "TLicni_podaci", propOrder = {"jmbg", "pol"})
@Getter
@Setter
public class LicniPodaci extends ImePrezime {

    @XmlElement(name = "jmbg", required = true)
    private RDFString jmbg;

    @XmlElement(name = "pol", required = true)
    private RDFString pol;

    public LicniPodaci() {
        this.jmbg = RDFString.of("");
    }

    public LicniPodaci(RDFString ime, RDFString prezime, RDFString jmbg, RDFString pol) {
        super(ime, prezime);
        this.jmbg = jmbg;
        this.pol = pol;
    }
}

