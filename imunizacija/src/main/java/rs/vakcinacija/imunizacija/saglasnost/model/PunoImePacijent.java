package rs.vakcinacija.imunizacija.saglasnost.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "puno_ime")
@XmlType(name = "TPunoIme", propOrder = {"ime", "prezime", "imeRoditelja"})
public class PunoImePacijent {

    @XmlElement(name = "ime", required = true, namespace = "https://www.vakcinacija.rs/zajednicko")
    private String ime;

    @XmlElement(name = "prezime", required = true, namespace = "https://www.vakcinacija.rs/zajednicko")
    private String prezime;

    @XmlElement(name = "ime_roditelja", required = true)
    private String imeRoditelja;

    public PunoImePacijent() {}

    public PunoImePacijent(String ime, String prezime, String imeRoditelja) {
        this.ime = ime;
        this.prezime = prezime;
        this.imeRoditelja = imeRoditelja;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getImeRoditelja() {
        return imeRoditelja;
    }

    public void setImeRoditelja(String imeRoditelja) {
        this.imeRoditelja = imeRoditelja;
    }

}
