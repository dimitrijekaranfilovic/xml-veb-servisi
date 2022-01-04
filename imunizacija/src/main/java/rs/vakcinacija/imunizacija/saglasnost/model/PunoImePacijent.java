package rs.vakcinacija.imunizacija.saglasnost.model;

import rs.vakcinacija.imunizacija.zajednicko.model.ImePrezime;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "puno_ime")
@XmlType(name = "TPunoIme", propOrder = {"imeRoditelja"})
public class PunoImePacijent extends ImePrezime {

    @XmlElement(name = "ime_roditelja", required = true)
    private String imeRoditelja;

    public PunoImePacijent() {
        super();
    }

    public PunoImePacijent(String ime, String prezime, String imeRoditelja) {
        super(ime, prezime);
        this.imeRoditelja = imeRoditelja;
    }

    public String getImeRoditelja() {
        return imeRoditelja;
    }

    public void setImeRoditelja(String imeRoditelja) {
        this.imeRoditelja = imeRoditelja;
    }

}
