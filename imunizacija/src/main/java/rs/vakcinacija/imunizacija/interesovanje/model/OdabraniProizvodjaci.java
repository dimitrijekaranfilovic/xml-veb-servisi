package rs.vakcinacija.imunizacija.interesovanje.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Collection;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "odabrani_proizvodjaci")
@XmlType(name = "TOdabraniProizvodjaci", propOrder = {"proizvodjaci"})
@Getter
@Setter
public class OdabraniProizvodjaci {

    @XmlElement(name = "proizvodjac", required = true)
    private Collection<RDFField<String>> proizvodjaci;
    
}
