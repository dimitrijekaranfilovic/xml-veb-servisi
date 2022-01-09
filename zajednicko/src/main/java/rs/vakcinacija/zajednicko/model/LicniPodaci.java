package rs.vakcinacija.zajednicko.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "licni_podaci")
@XmlType(name = "TLicni_podaci", propOrder = {"jmbg", "pol"})
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

    public RDFField<String> getJmbg() {
        return jmbg;
    }

    public void setJmbg(RDFField<String> jmbg) {
        this.jmbg = jmbg;
    }

    public RDFField<String> getPol() {
        return pol;
    }

    public void setPol(RDFField<String> pol) {
        this.pol = pol;
    }
}

