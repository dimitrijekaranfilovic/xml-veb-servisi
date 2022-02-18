@XmlSchema(
        namespace = "https://www.vakcinacija.rs/zahtev_za_sertifikat",
        elementFormDefault = XmlNsForm.QUALIFIED,
        xmlns = {
                @javax.xml.bind.annotation.XmlNs(prefix = "za", namespaceURI = "https://www.vakcinacija.rs/zajednicko"),
                @javax.xml.bind.annotation.XmlNs(prefix = "pred", namespaceURI = "https://www.vakcinacija.rs/rdf/predicate/"),
                @javax.xml.bind.annotation.XmlNs(prefix = "", namespaceURI = "https://www.vakcinacija.rs/zahtev_za_sertifikat"),
                @javax.xml.bind.annotation.XmlNs(prefix = "xsi", namespaceURI = "http://www.w3.org/2001/XMLSchema-instance"),
                @javax.xml.bind.annotation.XmlNs(prefix = "xs", namespaceURI = "http://www.w3.org/2001/XMLSchema")}
)
package rs.vakcinacija.imunizacija.zahtevzasertifikat.model;

import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;