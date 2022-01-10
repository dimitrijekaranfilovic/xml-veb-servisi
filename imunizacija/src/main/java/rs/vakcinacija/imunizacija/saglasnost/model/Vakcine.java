package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Collection;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vakcine")
@XmlType(name = "TVakcine", propOrder = {"vakcine"})
@Getter
@Setter
public class Vakcine extends RDFField {

    @XmlElement(name="vakcina", required=false)
    private Collection<Vakcina> vakcine;

    public Vakcine() {}

    public Vakcine(Collection<Vakcina> vakcine) {
        this.vakcine = vakcine;
    }
}
