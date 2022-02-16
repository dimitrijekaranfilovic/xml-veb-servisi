package rs.vakcinacija.imunizacija.saglasnost.dto;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "punoIme")
@XmlType(name = "TPunoIme", propOrder = {"imeRoditelja"})
@Getter
@Setter
public class PunoImePacijentDTO extends ImePrezimeDTO {

    @XmlElement(name = "imeRoditelja", required = true)
    private RDFString imeRoditelja;

    public PunoImePacijentDTO() {}

    public PunoImePacijentDTO(RDFString imeRoditelja) {
        this.imeRoditelja = imeRoditelja;
    }

    public PunoImePacijentDTO(RDFString ime, RDFString prezime, RDFString imeRoditelja) {
        super(ime, prezime);
        this.imeRoditelja = imeRoditelja;
    }
}
