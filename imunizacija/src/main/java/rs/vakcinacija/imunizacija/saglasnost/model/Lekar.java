package rs.vakcinacija.imunizacija.saglasnost.model;

import rs.vakcinacija.zajednicko.model.ImePrezime;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "lekar")
@XmlType(name = "TLekar", propOrder = {"telefon"})
public class Lekar extends ImePrezime {

    @XmlElement(name = "telefon", required = true)
    private RDFField<Telefon> telefon;

    public Lekar() {
        super();
    }

    public Lekar(RDFField<String> ime, RDFField<String> prezime, RDFField<Telefon> telefon) {
        super(ime, prezime);
        this.telefon = telefon;
    }

    public RDFField<Telefon> getTelefon() {
        return telefon;
    }

    public void setTelefon(RDFField<Telefon> telefon) {
        this.telefon = telefon;
    }
}
