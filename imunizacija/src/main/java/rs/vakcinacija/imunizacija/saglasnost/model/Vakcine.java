package rs.vakcinacija.imunizacija.saglasnost.model;

import javax.xml.bind.annotation.*;
import java.util.Collection;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vakcine")
@XmlType(name = "TVakcine", propOrder = {"vakcine"})
public class Vakcine {

    @XmlElement(name="vakcina", required=false)
    private Collection<Vakcina> vakcine;

    public Vakcine() {}

    public Vakcine(Collection<Vakcina> vakcine) {
        this.vakcine = vakcine;
    }

    public Collection<Vakcina> getVakcine() {
        return vakcine;
    }

    public void setVakcine(Collection<Vakcina> vakcine) {
        this.vakcine = vakcine;
    }
}
