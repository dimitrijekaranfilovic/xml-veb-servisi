package rs.vakcinacija.imunizacija.saglasnost.model;

import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Collection;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vakcine")
@XmlType(name = "TVakcine", propOrder = {"vakcine"})
public class Vakcine {

    @XmlElement(name="vakcina", required=false)
    private RDFField<Collection<Vakcina>> vakcine;

    public Vakcine() {}

    public Vakcine(RDFField<Collection<Vakcina>> vakcine) {
        this.vakcine = vakcine;
    }

    public RDFField<Collection<Vakcina>> getVakcine() {
        return vakcine;
    }

    public void setVakcine(RDFField<Collection<Vakcina>> vakcine) {
        this.vakcine = vakcine;
    }
}
