package rs.vakcinacija.imunizacija.saglasnost.dto;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "puno_ime")
@XmlType(name = "TIme_prezime", propOrder = {"ime", "prezime"})
@Getter
@Setter
public class ImePrezimeDTO {

    @XmlElement(name = "ime", required = true)
    private RDFString ime;

    @XmlElement(name = "prezime", required = true)
    private RDFString prezime;

    public ImePrezimeDTO() {
    }

    public ImePrezimeDTO(RDFString ime, RDFString prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }
}
