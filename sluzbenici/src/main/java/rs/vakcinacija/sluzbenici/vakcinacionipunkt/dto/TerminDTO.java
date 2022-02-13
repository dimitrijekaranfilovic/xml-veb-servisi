package rs.vakcinacija.sluzbenici.vakcinacionipunkt.dto;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlRootElement(name = "termin")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class TerminDTO {

    @XmlElement(name = "datum_vreme")
    private XMLGregorianCalendar datumVreme;

    public TerminDTO(XMLGregorianCalendar datumVreme) {
        this.datumVreme = datumVreme;
    }

    public TerminDTO() {
        super();
    }
}
