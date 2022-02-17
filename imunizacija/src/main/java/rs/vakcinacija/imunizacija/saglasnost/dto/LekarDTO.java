package rs.vakcinacija.imunizacija.saglasnost.dto;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "lekar")
@Getter
@Setter
public class LekarDTO {

    @XmlElement(name = "telefon")
    private TelefonDTO telefonDTO;

    @XmlElement(name = "ime")
    private String ime;

    @XmlElement(name = "prezime")
    private String prezime;

    public LekarDTO(TelefonDTO telefonDTO, String ime, String prezime) {
        this.telefonDTO = telefonDTO;
        this.ime = ime;
        this.prezime = prezime;
    }

    public LekarDTO() {
    }
}