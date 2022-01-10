package rs.vakcinacija.zajednicko.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"value"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RDFString {
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
}
