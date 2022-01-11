package rs.vakcinacija.zajednicko.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.metadata.RDFMetadataField;
import rs.vakcinacija.zajednicko.metadata.builder.RDFMetadataBuilder;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@Getter
@Setter
public class RDFField implements RDFMetadataField {

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

    @Override
    public RDFMetadataBuilder rdf() {
        return RDFMetadataBuilder.of(this);
    }
}
