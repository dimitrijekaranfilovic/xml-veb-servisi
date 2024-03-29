package rs.vakcinacija.zajednicko.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.vakcinacija.zajednicko.metadata.RDFMetadataField;
import rs.vakcinacija.zajednicko.metadata.builder.RDFMetadataBuilder;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"value"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RDFDate implements RDFMetadataField, Serializable {
    @XmlValue
    @XmlSchemaType(name = "date")
    private Date value;

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

    public static RDFDate of(Date date) {
        var rdfDate = new RDFDate();
        rdfDate.setValue(date);
        return rdfDate;
    }

    @Override
    public RDFMetadataBuilder rdf() {
        return RDFMetadataBuilder.of(this);
    }
}
