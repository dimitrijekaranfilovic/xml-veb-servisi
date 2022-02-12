package rs.vakcinacija.imunizacija.saglasnost.dto;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFBoolean;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "saglasnost")
@XmlType(name = "TSaglasnost", propOrder = {"izjava", "nazivImunoloskogLeka"})
@Getter
@Setter
public class SaglasnostDTO {

    @XmlElement(name = "izjava", required = true)
    private RDFBoolean izjava;

    @XmlElement(name = "nazivImunoloskogLeka", required = true)
    private RDFString nazivImunoloskogLeka;

    public SaglasnostDTO() {}

    public SaglasnostDTO(RDFBoolean izjava, RDFString nazivImunoloskogLeka) {
        this.izjava = izjava;
        this.nazivImunoloskogLeka = nazivImunoloskogLeka;
    }
}
