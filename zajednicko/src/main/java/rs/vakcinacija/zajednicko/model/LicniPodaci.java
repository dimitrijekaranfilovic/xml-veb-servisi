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
    private RDFField<String> jmbg;

    @XmlElement(name = "pol", required = true)
    private RDFField<String> pol;

    public LicniPodaci() {
    }

    public LicniPodaci(RDFField<String> ime, RDFField<String> prezime, RDFField<String> jmbg, RDFField<String> pol) {
        super(ime, prezime);
        this.jmbg = jmbg;
        this.pol = pol;
    }
}

