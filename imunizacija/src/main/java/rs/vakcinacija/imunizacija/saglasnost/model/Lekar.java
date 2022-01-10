package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.ImePrezime;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "lekar")
@XmlType(name = "TLekar", propOrder = {"telefon"})
@Getter
@Setter
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
}
