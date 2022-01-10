package rs.vakcinacija.sluzbenici.digitalnisertifikat.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Collection;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vakcinacija")
@XmlType(name = "TVakcinacija", propOrder = {"vakcine"})
@Getter
@Setter
public class Vakcinacija extends RDFField {

    @XmlElement(name = "doza_vakcine", required = true)
    Collection<DozaVakcine> vakcine;

    public Vakcinacija() {
    }

    public Vakcinacija(Collection<DozaVakcine> vakcine) {
        this.vakcine = vakcine;
    }
}
