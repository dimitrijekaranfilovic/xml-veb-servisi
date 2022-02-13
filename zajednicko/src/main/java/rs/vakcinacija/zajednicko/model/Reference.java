package rs.vakcinacija.zajednicko.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "reference")
@XmlType(name = "TReference", propOrder = {"reference"})
@Getter
@Setter
public class Reference extends RDFField {

    @XmlElement(name = "referenca")
    Collection<RDFUUID> reference;

    public Reference() {
        this.reference = new ArrayList<>();
    }

    public Reference(Collection<RDFUUID> reference) {
        this.reference = reference;
    }
}
