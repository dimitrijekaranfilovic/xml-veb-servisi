package rs.vakcinacija.imunizacija.saglasnost.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "lekar")
@XmlType(name = "TLekar", propOrder = {"ime", "prezime", "telefon"})
public class Lekar {

    @XmlElement(name = "ime", required = true, namespace = "https://www.vakcinacija.rs/zajednicko")
    private String ime;

    @XmlElement(name = "prezime", required = true, namespace = "https://www.vakcinacija.rs/zajednicko")
    private String prezime;

    @XmlElement(name = "telefon", required = true)
    private Telefon telefon;

    public Lekar() {}

    public Lekar(String ime, String prezime, Telefon telefon) {
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
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

    public Telefon getTelefon() {
        return telefon;
    }

    public void setTelefon(Telefon telefon) {
        this.telefon = telefon;
    }
}
