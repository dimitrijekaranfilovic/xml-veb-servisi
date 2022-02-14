package rs.vakcinacija.imunizacija.zahtevzasertifikat.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.LicniPodaci;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.model.RDFField;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "podnosilac_zahteva")
@XmlType(name = "TPodnosilacZahteva", propOrder = {"licniPodaci", "datumRodjenja", "brojPasosa"})
@Getter
@Setter
public class PodnosilacZahteva extends RDFField {

    @XmlElement(name = "licni_podaci", required = true)
    private LicniPodaci licniPodaci;

    @XmlElement(name = "datum_rodjenja", required = true)
    private RDFDate datumRodjenja;

    @XmlElement(name = "broj_pasosa", required = true)
    private RDFString brojPasosa;


    public PodnosilacZahteva() {
    }

    public PodnosilacZahteva(LicniPodaci licniPodaci, RDFDate datumRodjenja, RDFString brojPasosa) {
        this.licniPodaci = licniPodaci;
        this.datumRodjenja = datumRodjenja;
        this.brojPasosa = brojPasosa;
    }
}
