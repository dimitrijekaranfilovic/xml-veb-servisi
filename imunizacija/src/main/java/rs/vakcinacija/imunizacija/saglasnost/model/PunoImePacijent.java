package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.ImePrezime;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "puno_ime")
@XmlType(name = "TPunoIme", propOrder = {"imeRoditelja"})
@Getter
@Setter
public class PunoImePacijent extends ImePrezime {

    @XmlElement(name = "ime_roditelja", required = true)
    private RDFString imeRoditelja;

    public PunoImePacijent() {
        super();
    }

    public PunoImePacijent(RDFString ime, RDFString prezime, RDFString imeRoditelja) {
        super(ime, prezime);
        this.imeRoditelja = imeRoditelja;
    }
}
