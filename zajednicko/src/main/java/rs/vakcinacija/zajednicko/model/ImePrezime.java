package rs.vakcinacija.zajednicko.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "puno_ime")
@XmlType(name = "TIme_prezime", propOrder = {"ime", "prezime"})
@Getter
@Setter
public class ImePrezime extends RDFField {

    @XmlElement(name = "ime", required = true)
    private RDFString ime;

    @XmlElement(name = "prezime", required = true)
    private RDFString prezime;

    public ImePrezime() {
    }

    public ImePrezime(RDFString ime, RDFString prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }
}