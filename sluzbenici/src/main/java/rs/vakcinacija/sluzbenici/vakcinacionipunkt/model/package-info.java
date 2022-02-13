
@XmlSchema(
        namespace = "https://www.vakcinacija.rs/vakcinacioni_punkt",
        elementFormDefault = XmlNsForm.QUALIFIED,
        xmlns = {
                @javax.xml.bind.annotation.XmlNs(prefix = "za", namespaceURI = "https://www.vakcinacija.rs/zajednicko"),
                @javax.xml.bind.annotation.XmlNs(prefix = "pred", namespaceURI = "https://www.vakcinacija.rs/rdf/predicate/"),
                @javax.xml.bind.annotation.XmlNs(prefix = "", namespaceURI = "https://www.vakcinacija.rs/vakcinacioni_punkt"),
                @javax.xml.bind.annotation.XmlNs(prefix = "xs", namespaceURI = "http://www.w3.org/2001/XMLSchema")}
)

package rs.vakcinacija.sluzbenici.vakcinacionipunkt.model;

import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;