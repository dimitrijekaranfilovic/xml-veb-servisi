package rs.vakcinacija.sluzbenici.saglasnost.dto;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "podaci_o_lekaru_ustanovi")
@Getter
@Setter
public class PodaciOLekaruUstanoviDTO {

    @XmlElement(name = "lekar")
    private LekarDTO lekarDTO;

    @XmlElement(name = "zdravstvena_ustanova")
    private ZdravstvenaUstanovaDTO zdravstvenaUstanovaDTO;

    public PodaciOLekaruUstanoviDTO(LekarDTO lekarDTO, ZdravstvenaUstanovaDTO zdravstvenaUstanovaDTO) {
        this.lekarDTO = lekarDTO;
        this.zdravstvenaUstanovaDTO = zdravstvenaUstanovaDTO;
    }

    public PodaciOLekaruUstanoviDTO() {
    }
}
