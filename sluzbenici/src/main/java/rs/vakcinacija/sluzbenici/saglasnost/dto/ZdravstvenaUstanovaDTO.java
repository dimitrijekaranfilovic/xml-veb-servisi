package rs.vakcinacija.sluzbenici.saglasnost.dto;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "zdravstvena_ustanova")
@Getter
@Setter
public class ZdravstvenaUstanovaDTO {

    @XmlElement(name = "naziv")
    private String naziv;

    @XmlElement(name = "punkt")
    private Integer punkt;

    public ZdravstvenaUstanovaDTO(String naziv, Integer punkt) {
        this.naziv = naziv;
        this.punkt = punkt;
    }

    public ZdravstvenaUstanovaDTO() {
    }
}
