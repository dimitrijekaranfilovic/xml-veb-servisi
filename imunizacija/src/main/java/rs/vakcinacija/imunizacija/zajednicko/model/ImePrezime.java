package rs.vakcinacija.imunizacija.zajednicko.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "puno_ime")
@XmlType(name = "TIme_prezime", propOrder = {"ime", "prezime"})
@XmlSeeAlso({LicniPodaci.class})
public class ImePrezime {

    @XmlElement(name = "ime", required = true)
    private String ime;

    @XmlElement(name = "prezime", required = true)
    private String prezime;

    public ImePrezime() {
    }

    public ImePrezime(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
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
}
