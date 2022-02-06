package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFBoolean;
import rs.vakcinacija.zajednicko.model.RDFField;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "socijalna_zastita")
@XmlType(name = "TSocijalnaZastita", propOrder = {"korisnikUstanove", "nazivSedista", "opstinaSedista"})
@Getter
@Setter
public class SocijalnaZastita extends RDFField {

    @XmlElement(name = "korisnik_ustanove_socijalne_zastite", required = true)
    private RDFBoolean korisnikUstanove;

    @XmlElement(name = "naziv_sedista", required = false)
    private RDFString nazivSedista;

    @XmlElement(name = "opstina_sedista", required = false)
    private RDFString opstinaSedista;

    public SocijalnaZastita() {}

    public SocijalnaZastita(RDFBoolean korisnikUstanove, RDFString nazivSedista, RDFString opstinaSedista) {
        this.korisnikUstanove = korisnikUstanove;
        this.nazivSedista = nazivSedista;
        this.opstinaSedista = opstinaSedista;
    }
}
