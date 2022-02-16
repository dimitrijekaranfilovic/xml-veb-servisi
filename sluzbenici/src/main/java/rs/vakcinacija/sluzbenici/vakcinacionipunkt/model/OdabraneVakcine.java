package rs.vakcinacija.sluzbenici.vakcinacionipunkt.model;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;

@XmlRootElement(name = "odabrane_vakcine")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TOdabrane_vakcine")
@Getter
@Setter
public class OdabraneVakcine {

    @XmlElement(name = "odabrana_vakcina")
    private Collection<String> odabraneVakcine;

    public OdabraneVakcine(Collection<String> odabraneVakcine) {
        this.odabraneVakcine = odabraneVakcine;
    }

    public OdabraneVakcine() {
        this.odabraneVakcine = new ArrayList<>();
    }
}
