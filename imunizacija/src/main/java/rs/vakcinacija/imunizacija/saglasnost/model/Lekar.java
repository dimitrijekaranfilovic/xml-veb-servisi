package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.ImePrezime;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "lekar")
@XmlType(name = "TLekar", propOrder = {"telefon"})
@Getter
@Setter
public class Lekar extends ImePrezime {

    @XmlElement(name = "telefon", required = true)
    private Telefon telefon;

    public Lekar() {
        super();
    }

    public Lekar(RDFString ime, RDFString prezime, UUID id, Telefon telefon) {
        super(ime, prezime);
        this.telefon = telefon;
    }
}
