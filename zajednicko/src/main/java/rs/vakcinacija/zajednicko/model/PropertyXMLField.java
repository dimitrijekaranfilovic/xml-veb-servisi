package rs.vakcinacija.zajednicko.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "property_field")
public class PropertyXMLField<T> {
    @XmlAttribute(name = "property")
    private String property;

    @XmlAttribute(name = "datatype")
    private String datatype;

    @XmlValue
    private T value;

    public PropertyXMLField() {
    }

    public PropertyXMLField(String property, String datatype, T value) {
        this.property = property;
        this.datatype = datatype;
        this.value = value;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
