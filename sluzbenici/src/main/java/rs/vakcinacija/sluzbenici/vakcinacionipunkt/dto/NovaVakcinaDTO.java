package rs.vakcinacija.sluzbenici.vakcinacionipunkt.dto;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "nova_vakcina")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class NovaVakcinaDTO {

    @XmlElement(name = "naziv_vakcine")
    private String nazivVakcine;

    @XmlElement(name = "stanje_vakcine")
    private Integer stanjeVakcine;


    public NovaVakcinaDTO(String nazivVakcine, Integer stanjeVakcine) {
        this.nazivVakcine = nazivVakcine;
        this.stanjeVakcine = stanjeVakcine;
    }

    public NovaVakcinaDTO() {
    }
}
