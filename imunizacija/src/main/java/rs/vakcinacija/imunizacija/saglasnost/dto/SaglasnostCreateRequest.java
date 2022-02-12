package rs.vakcinacija.imunizacija.saglasnost.dto;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "saglasnostZaSprovodjenjeImunizacije")
@Getter
@Setter
public class SaglasnostCreateRequest {

    @XmlElement(name = "pacijent", required = true)
    private PacijentDTO pacijent;

    public SaglasnostCreateRequest() {}

    public SaglasnostCreateRequest(PacijentDTO pacijent) {
        this.pacijent = pacijent;
    }
}
