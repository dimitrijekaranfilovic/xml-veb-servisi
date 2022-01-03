package rs.vakcinacija.sluzbenici.digitalnisertifikat.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "informacije_o_sertifikatu")
@XmlType(name = "TInformacije_o_sertifikatu", propOrder = {"qrKod", "digitalniPotpis"})
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

    public String getQrKod() {
        return qrKod;
    }

    public void setQrKod(String qrKod) {
        this.qrKod = qrKod;
    }

    public DigitalniPotpis getDigitalniPotpis() {
        return digitalniPotpis;
    }

    public void setDigitalniPotpis(DigitalniPotpis digitalniPotpis) {
        this.digitalniPotpis = digitalniPotpis;
    }
}
