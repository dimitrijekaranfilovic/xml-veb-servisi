package rs.vakcinacija.imunizacija.zahtevzasertifikat.model;

import rs.vakcinacija.zajednicko.model.LicniPodaci;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "podnosilac_zahteva")
@XmlType(name = "TPodnosilacZahteva", propOrder = {"licniPodaci", "brojPasosa"})
public class PodnosilacZahteva {

    @XmlElement(name = "licni_podaci", required = true)
    private LicniPodaci licniPodaci;

    @XmlElement(name = "broj_pasosa", required = true)
    private String brojPasosa;


    public PodnosilacZahteva() {
    }

    public PodnosilacZahteva(LicniPodaci licniPodaci, String brojPasosa) {
        this.licniPodaci = licniPodaci;
        this.brojPasosa = brojPasosa;
    }

    public LicniPodaci getLicniPodaci() {
        return licniPodaci;
    }

    public void setLicniPodaci(LicniPodaci licniPodaci) {
        this.licniPodaci = licniPodaci;
    }

    public String getBrojPasosa() {
        return brojPasosa;
    }

    public void setBrojPasosa(String brojPasosa) {
        this.brojPasosa = brojPasosa;
    }
}
