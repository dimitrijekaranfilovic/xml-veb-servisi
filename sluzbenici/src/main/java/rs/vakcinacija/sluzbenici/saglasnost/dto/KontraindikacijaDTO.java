package rs.vakcinacija.sluzbenici.saglasnost.dto;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "kontraindikacija")
@Getter
@Setter
public class KontraindikacijaDTO {

    @XmlElement(name = "datum_utvrdjivanja")
    @XmlSchemaType(name = "date")
    private Date datumUtvrdjivanja;

    @XmlElement(name = "dijagnoza")
    private String dijagnoza;

    public KontraindikacijaDTO(Date datumUtvrdjivanja, String dijagnoza) {
        this.datumUtvrdjivanja = datumUtvrdjivanja;
        this.dijagnoza = dijagnoza;
    }

    public KontraindikacijaDTO() {
    }
}
