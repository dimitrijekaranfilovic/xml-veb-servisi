package rs.vakcinacija.zajednicko.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "reference_field")
@XmlJavaTypeAdapter(RDFFieldAdapter.class)
@Getter
@Setter
public class RDFField<T> {

    @XmlAttribute(name = "property")
    private String property;

    @XmlAttribute(name = "datatype")
    private String datatype;

    @XmlAttribute(name = "vocab")
    private String vocab;

    @XmlAttribute(name = "about")
    private String about;

    @XmlAttribute(name = "rel")
    private String rel;

    @XmlAttribute(name = "typeof")
    private String typeof;

    @XmlAttribute(name = "href")
    private String href;

    @XmlValue
    private T value;

    public RDFField() {}

    public RDFField(String property, String datatype, String vocab, String about, String rel, String typeof, String href, T value) {
        this.property = property;
        this.datatype = datatype;
        this.vocab = vocab;
        this.about = about;
        this.rel = rel;
        this.typeof = typeof;
        this.href = href;
        this.value = value;
    }

    public RDFField(T value) {
        this.value = value;
    }

    public RDFField(String property, String datatype, T value) {
        this.property = property;
        this.datatype = datatype;
        this.value = value;
    }

    public RDFField(String vocab, String about, String rel, String typeof, String href, T value) {
        this.vocab = vocab;
        this.about = about;
        this.rel = rel;
        this.typeof = typeof;
        this.href = href;
        this.value = value;
    }
}
