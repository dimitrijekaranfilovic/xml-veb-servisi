package rs.vakcinacija.zajednicko.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@Getter
@Setter
public class BaseDocument extends RDFField {
    @XmlElement(name = "id", required = true)
    protected UUID id;

    @XmlElement(name = "reference")
    protected Reference reference = new Reference();

    public ReferenceBuilder ref(String name) {
        return ReferenceBuilder.of(this.reference, name);
    }
}
