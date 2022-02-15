package rs.vakcinacija.zajednicko.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.vakcinacija.zajednicko.metadata.RDFMetadataField;
import rs.vakcinacija.zajednicko.metadata.builder.RDFMetadataBuilder;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"value"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RDFUUID implements RDFMetadataField, Serializable {
    @XmlValue
    private UUID value;

    @XmlAttribute(name = "property")
    protected String property;

    @XmlAttribute(name = "datatype")
    protected String datatype;

    @XmlAttribute(name = "vocab")
    protected String vocab;

    @XmlAttribute(name = "about")
    protected String about;

    @XmlAttribute(name = "rel")
    protected String rel;

    @XmlAttribute(name = "typeof")
    protected String typeof;

    @XmlAttribute(name = "href")
    protected String href;

    public static RDFUUID of(UUID uuid) {
        var rdfUuid = new RDFUUID();
        rdfUuid.setValue(uuid);
        return rdfUuid;
    }

    @Override
    public RDFMetadataBuilder rdf() {
        return RDFMetadataBuilder.of(this);
    }
}
