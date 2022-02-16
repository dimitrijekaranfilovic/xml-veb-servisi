package rs.vakcinacija.sluzbenici.vakcinacionipunkt.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.UUID;

@XmlRootElement(name = "zainteresovani_pacijent")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TZainteresovani_pacijent")
@Getter
@Setter
public class ZainteresovaniPacijent {

    @XmlElement(name = "odabrane_vakcine")
    private OdabraneVakcine odabraneVakcine;

    @XmlElement(name = "email")
    private String email;

    @XmlElement(name = "interesovanje_id")
    private UUID interesovanjeId;

    public ZainteresovaniPacijent() {
        this.odabraneVakcine = new OdabraneVakcine();
    }

    public ZainteresovaniPacijent(OdabraneVakcine odabraneVakcine, String email, UUID interesovanjeId) {
        this.odabraneVakcine = odabraneVakcine;
        this.email = email;
        this.interesovanjeId = interesovanjeId;
    }
}
