package rs.vakcinacija.imunizacija.interesovanje.model;

import javax.xml.bind.annotation.*;
import java.util.Collection;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "odabrani_proizvodjaci")
@XmlType(name = "TOdabraniProizvodjaci", propOrder = {"proizvodjaci"})
public class OdabraniProizvodjaci {

    @XmlElement(name = "proizvodjac", required = true)
    private Collection<String> proizvodjaci;

    public OdabraniProizvodjaci() {
    }

    public OdabraniProizvodjaci(Collection<String> proizvodjaci) {
        this.proizvodjaci = proizvodjaci;
    }

    public Collection<String> getProizvodjaci() {
        return proizvodjaci;
    }

    public void setProizvodjaci(Collection<String> proizvodjaci) {
        this.proizvodjaci = proizvodjaci;
    }
}
