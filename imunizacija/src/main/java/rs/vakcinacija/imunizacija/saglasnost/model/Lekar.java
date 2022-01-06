package rs.vakcinacija.imunizacija.saglasnost.model;

import rs.vakcinacija.zajednicko.model.ImePrezime;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "lekar")
@XmlType(name = "TLekar", propOrder = {"telefon"})
public class Lekar extends ImePrezime {

    @XmlElement(name = "telefon", required = true)
    private Telefon telefon;

    public Lekar() {
        super();
    }

    public Lekar(String ime, String prezime, Telefon telefon) {
        super(ime, prezime);
        this.telefon = telefon;
    }

    public Telefon getTelefon() {
        return telefon;
    }

    public void setTelefon(Telefon telefon) {
        this.telefon = telefon;
    }
}
