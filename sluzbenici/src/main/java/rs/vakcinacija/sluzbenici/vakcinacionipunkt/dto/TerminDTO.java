package rs.vakcinacija.sluzbenici.vakcinacionipunkt.dto;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement(name = "termin")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class TerminDTO {

    @XmlElement(name = "datum_vreme")
    @XmlSchemaType(name = "dateTime")
    private Date datumVreme;

    public TerminDTO(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public TerminDTO() {
        super();
    }
}
