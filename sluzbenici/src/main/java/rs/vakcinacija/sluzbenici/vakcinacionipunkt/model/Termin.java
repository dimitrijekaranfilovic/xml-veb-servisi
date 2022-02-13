package rs.vakcinacija.sluzbenici.vakcinacionipunkt.model;


import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.sluzbenici.config.LocalDateTimeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@XmlRootElement(name = "termin")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTermin")
@Getter
@Setter
public class Termin {

    @XmlValue
    @XmlSchemaType(name = "dateTime")
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    private LocalDateTime datumIVreme;

    public Termin(LocalDateTime datumIVreme) {
        this.datumIVreme = datumIVreme;
    }

    public Termin() {
        super();
    }
}
