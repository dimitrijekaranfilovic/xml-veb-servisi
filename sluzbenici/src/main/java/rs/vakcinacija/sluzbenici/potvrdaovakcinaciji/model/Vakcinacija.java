package rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model;


import javax.xml.bind.annotation.*;

@XmlRootElement(name = "vakcinacija")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TVakcinacija")
public class Vakcinacija {

    @XmlElement(name = "ustanova", required = true)
    private String ustanova;

    @XmlElement(name = "naziv_vakcine", required = true)
    private String nazivVakcine;

    @XmlElement(name = "doze", required = true)
    private Doze doze;


    public Vakcinacija(){
        super();
    }

    public Vakcinacija(String ustanova, String nazivVakcine, Doze doze) {
        this.ustanova = ustanova;
        this.nazivVakcine = nazivVakcine;
        this.doze = doze;
    }

    public String getUstanova() {
        return ustanova;
    }

    public void setUstanova(String ustanova) {
        this.ustanova = ustanova;
    }

    public String getNazivVakcine() {
        return nazivVakcine;
    }

    public void setNazivVakcine(String nazivVakcine) {
        this.nazivVakcine = nazivVakcine;
    }

    public Doze getDoze() {
        return doze;
    }

    public void setDoze(Doze doze) {
        this.doze = doze;
    }
}
