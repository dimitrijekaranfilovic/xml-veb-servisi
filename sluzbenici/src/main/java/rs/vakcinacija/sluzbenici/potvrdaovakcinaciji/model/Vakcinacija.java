package rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model;


import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "vakcinacija")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TVakcinacija")
public class Vakcinacija {

    @XmlElement(name = "ustanova", required = true)
    private RDFField<String> ustanova;

    @XmlElement(name = "naziv_vakcine", required = true)
    private RDFField<String> nazivVakcine;

    @XmlElement(name = "doze", required = true)
    private RDFField<Doze> doze;


    public Vakcinacija(){
        super();
    }

    public Vakcinacija(RDFField<String> ustanova,
                       RDFField<String> nazivVakcine,
                       RDFField<Doze> doze) {
        this.ustanova = ustanova;
        this.nazivVakcine = nazivVakcine;
        this.doze = doze;
    }

    public RDFField<String> getUstanova() {
        return ustanova;
    }

    public void setUstanova(RDFField<String> ustanova) {
        this.ustanova = ustanova;
    }

    public RDFField<String> getNazivVakcine() {
        return nazivVakcine;
    }

    public void setNazivVakcine(RDFField<String> nazivVakcine) {
        this.nazivVakcine = nazivVakcine;
    }

    public RDFField<Doze> getDoze() {
        return doze;
    }

    public void setDoze(RDFField<Doze> doze) {
        this.doze = doze;
    }
}
