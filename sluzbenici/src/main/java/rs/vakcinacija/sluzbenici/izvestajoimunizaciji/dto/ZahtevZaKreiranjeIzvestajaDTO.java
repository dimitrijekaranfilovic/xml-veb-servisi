package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.dto;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement(name = "zahtev_za_kreiranje_izvestaja")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class ZahtevZaKreiranjeIzvestajaDTO {

    @XmlElement(name = "period_od", required = true)
    @XmlSchemaType(name = "date")
    private Date periodOd;

    @XmlElement(name = "period_do", required = true)
    @XmlSchemaType(name = "date")
    private Date periodDo;

    public ZahtevZaKreiranjeIzvestajaDTO(Date periodOd, Date periodDo) {
        this.periodOd = periodOd;
        this.periodDo = periodDo;
    }

    public ZahtevZaKreiranjeIzvestajaDTO() {
    }
}
