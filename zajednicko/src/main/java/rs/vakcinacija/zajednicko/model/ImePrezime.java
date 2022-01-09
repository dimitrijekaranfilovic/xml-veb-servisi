package rs.vakcinacija.zajednicko.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "puno_ime")
@XmlType(name = "TIme_prezime", propOrder = {"ime", "prezime"})
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

    public RDFField<String> getIme() {
        return ime;
    }

    public void setIme(RDFField<String> ime) {
        this.ime = ime;
    }

    public RDFField<String> getPrezime() {
        return prezime;
    }

    public void setPrezime(RDFField<String> prezime) {
        this.prezime = prezime;
    }
}