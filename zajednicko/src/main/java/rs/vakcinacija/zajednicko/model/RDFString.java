package rs.vakcinacija.zajednicko.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.vakcinacija.zajednicko.metadata.RDFMetadataField;
import rs.vakcinacija.zajednicko.metadata.builder.RDFMetadataBuilder;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"value"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RDFString implements RDFMetadataField, Serializable {
    @XmlValue
    private String value;

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

    public static RDFString of(String string) {
        var rdfString = new RDFString();
        rdfString.setValue(string);
        return rdfString;
    }

    @Override
    public RDFMetadataBuilder rdf() {
        return RDFMetadataBuilder.of(this);
    }
}
