package rs.vakcinacija.sluzbenici.digitalnisertifikat.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "informacije_o_sertifikatu")
@XmlType(name = "TInformacije_o_sertifikatu", propOrder = {"qrKod", "digitalniPotpis"})
@Getter
@Setter
public class InformacijeOSertifikatu extends RDFField {

    @XmlElement(name = "qr_kod", required = true)
    private RDFString qrKod;

    @XmlElement(name = "digitalni_potpis", required = true)
    private DigitalniPotpis digitalniPotpis;

    public InformacijeOSertifikatu() {
    }

    public InformacijeOSertifikatu(RDFString qrKod, DigitalniPotpis digitalniPotpis) {
        this.qrKod = qrKod;
        this.digitalniPotpis = digitalniPotpis;
    }
}
