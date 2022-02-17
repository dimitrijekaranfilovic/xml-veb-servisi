package rs.vakcinacija.imunizacija.potvrdaovakcinaciji.model;


import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "vakcinacija")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TVakcinacija")
@Getter
@Setter
public class Vakcinacija extends RDFField {

    @XmlElement(name = "ustanova", required = true)
    private RDFString ustanova;

    @XmlElement(name = "naziv_vakcine", required = true)
    private RDFString nazivVakcine;

    @XmlElement(name = "doze", required = true)
    private Doze doze;


    public Vakcinacija() {
        super();
    }

    public Vakcinacija(RDFString ustanova,
                       RDFString nazivVakcine,
                       Doze doze) {
        this.ustanova = ustanova;
        this.nazivVakcine = nazivVakcine;
        this.doze = doze;
    }
}
