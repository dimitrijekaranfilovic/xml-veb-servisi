package rs.vakcinacija.sluzbenici.saglasnost.dto;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "telefon")
@Getter
@Setter
public class TelefonDTO {

    @XmlElement(name = "broj_fiksnog")
    public String brojFiksnog;

    @XmlElement(name = "broj_mobilnog")
    public String brojMobilnog;

    public TelefonDTO(String brojFiksnog, String brojMobilnog) {
        this.brojFiksnog = brojFiksnog;
        this.brojMobilnog = brojMobilnog;
    }

    public TelefonDTO() {
    }
}
