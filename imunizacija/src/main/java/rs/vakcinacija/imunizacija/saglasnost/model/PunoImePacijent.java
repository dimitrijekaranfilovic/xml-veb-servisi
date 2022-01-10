package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.ImePrezime;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "puno_ime")
@XmlType(name = "TPunoIme", propOrder = {"imeRoditelja"})
@Getter
@Setter
public class PunoImePacijent extends ImePrezime {

    @XmlElement(name = "ime_roditelja", required = true)
    private RDFField<String> imeRoditelja;

    public PunoImePacijent() {
        super();
    }

    public PunoImePacijent(RDFField<String> ime, RDFField<String> prezime, RDFField<String> imeRoditelja) {
        super(ime, prezime);
        this.imeRoditelja = imeRoditelja;
    }
}
