package rs.vakcinacija.sluzbenici.vakcinacionipunkt.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.Collection;

@XmlRootElement(name = "zainteresovani_pacijenti")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TZainteresovani_pacijenti")
@Getter
@Setter
public class ZainteresovaniPacijenti {

    @XmlElement(name = "zainteresovani_pacijent")
    private Collection<ZainteresovaniPacijent> zainteresovaniPacijenti;

    public ZainteresovaniPacijenti() {
    }

    public ZainteresovaniPacijenti(Collection<ZainteresovaniPacijent> zainteresovaniPacijenti) {
        this.zainteresovaniPacijenti = zainteresovaniPacijenti;
    }
}
