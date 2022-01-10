package rs.vakcinacija.zajednicko.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "puno_ime")
@XmlType(name = "TIme_prezime", propOrder = {"ime", "prezime"})
@Getter
@Setter
public class ImePrezime {

    @XmlElement(name = "ime", required = true)
    private RDFField<String> ime;

    @XmlElement(name = "prezime", required = true)
    private RDFField<String> prezime;

    public ImePrezime() {
    }

    public ImePrezime(RDFField<String> ime, RDFField<String> prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }
}