package rs.vakcinacija.sluzbenici.vakcinacionipunkt.model;


import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "dostupna_vakcina")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TDostupna_vakcina", propOrder = {"nazivVakcine", "stanjeVakcine"})
@Getter
@Setter
public class DostupnaVakcina {

    @XmlElement(name = "naziv_vakcine")
    private RDFString nazivVakcine;

    @XmlElement(name = "stanje_vakcine")
    private Integer stanjeVakcine;

    public DostupnaVakcina(RDFString nazivVakcine, Integer stanjeVakcine) {
        this.nazivVakcine = nazivVakcine;
        this.stanjeVakcine = stanjeVakcine;
    }

    public DostupnaVakcina() {
        super();
    }
}
