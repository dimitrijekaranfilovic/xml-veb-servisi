package rs.vakcinacija.imunizacija.zahtevzasertifikat.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.LicniPodaci;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "podnosilac_zahteva")
@XmlType(name = "TPodnosilacZahteva", propOrder = {"licniPodaci", "brojPasosa"})
@Getter
@Setter
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
}
