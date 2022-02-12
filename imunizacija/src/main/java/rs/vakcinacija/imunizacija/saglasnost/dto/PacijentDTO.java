package rs.vakcinacija.imunizacija.saglasnost.dto;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.imunizacija.saglasnost.model.LicneInformacije;
import rs.vakcinacija.imunizacija.saglasnost.model.Saglasnost;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "pacijent")
@XmlType(name = "TPacijent", propOrder = {"licneInformacije", "saglasnost"})
@Getter
@Setter
public class PacijentDTO {

    @XmlElement(name = "licneInformacije", required = true)
    private LicneInformacijeDTO licneInformacije;

    @XmlElement(name = "saglasnost", required = true)
    private SaglasnostDTO saglasnost;

    public PacijentDTO() {}

    public PacijentDTO(LicneInformacijeDTO licneInformacije, SaglasnostDTO saglasnost) {
        this.licneInformacije = licneInformacije;
        this.saglasnost = saglasnost;
    }
}
