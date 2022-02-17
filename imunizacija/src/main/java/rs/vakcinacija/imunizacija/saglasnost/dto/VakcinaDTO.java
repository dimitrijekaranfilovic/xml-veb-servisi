package rs.vakcinacija.imunizacija.saglasnost.dto;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vakcina")
@Getter
@Setter
public class VakcinaDTO {

    @XmlElement(name = "ekstremitet")
    private String ekstremitet;

    @XmlElement(name = "nuspojava")
    private String nuspojava;

    @XmlElement(name = "tip")
    private String tip;

    @XmlElement(name = "proizvodjac")
    private String proizvodjac;

    @XmlElement(name = "broj_doze")
    private Integer brojDoze;

    @XmlElement(name = "broj_serije")
    private String brojSerije;

    public VakcinaDTO(String ekstremitet, String nuspojava, String tip, String proizvodjac, Integer brojDoze, String brojSerije) {
        this.ekstremitet = ekstremitet;
        this.nuspojava = nuspojava;
        this.tip = tip;
        this.proizvodjac = proizvodjac;
        this.brojDoze = brojDoze;
        this.brojSerije = brojSerije;
    }

    public VakcinaDTO() {
    }
}
