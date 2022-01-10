package rs.vakcinacija.sluzbenici.digitalnisertifikat.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "informacije_o_sertifikatu")
@XmlType(name = "TInformacije_o_sertifikatu", propOrder = {"qrKod", "digitalniPotpis"})
@Getter
@Setter
public class InformacijeOSertifikatu {

    @XmlElement(name = "qr_kod", required = true)
    private String qrKod;

    @XmlElement(name = "digitalni_potpis", required = true)
    private DigitalniPotpis digitalniPotpis;

    public InformacijeOSertifikatu() {
    }

    public InformacijeOSertifikatu(String qrKod, DigitalniPotpis digitalniPotpis) {
        this.qrKod = qrKod;
        this.digitalniPotpis = digitalniPotpis;
    }
}
