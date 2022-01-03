@XmlSchema(
        namespace = "https://www.vakcinacija.rs/interesovanje",
        elementFormDefault = XmlNsForm.QUALIFIED,
        xmlns = {@javax.xml.bind.annotation.XmlNs(prefix = "za", namespaceURI = "https://www.vakcinacija.rs/zajednicko"),
                @javax.xml.bind.annotation.XmlNs(prefix = "", namespaceURI = "https://www.vakcinacija.rs/interesovanje"),
                @javax.xml.bind.annotation.XmlNs(prefix = "xs", namespaceURI = "http://www.w3.org/2001/XMLSchema")}
)
package rs.vakcinacija.imunizacija.interesovanje.model;

import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;