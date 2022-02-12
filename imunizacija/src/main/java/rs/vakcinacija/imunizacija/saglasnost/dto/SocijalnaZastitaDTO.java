package rs.vakcinacija.imunizacija.saglasnost.dto;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFBoolean;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "socijalnaZastita")
@XmlType(name = "TSocijalnaZastita", propOrder = {"korisnikUstanove", "nazivSedista", "opstinaSedista"})
@Getter
@Setter
public class SocijalnaZastitaDTO {

    @XmlElement(name = "korisnikUstanove", required = true)
    private RDFBoolean korisnikUstanove;

    @XmlElement(name = "nazivSedista", required = false)
    private RDFString nazivSedista;

    @XmlElement(name = "opstinaSedista", required = false)
    private RDFString opstinaSedista;

    public SocijalnaZastitaDTO() {}

    public SocijalnaZastitaDTO(RDFBoolean korisnikUstanove, RDFString nazivSedista, RDFString opstinaSedista) {
        this.korisnikUstanove = korisnikUstanove;
        this.nazivSedista = nazivSedista;
        this.opstinaSedista = opstinaSedista;
    }
}
