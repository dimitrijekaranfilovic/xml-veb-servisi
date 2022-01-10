package rs.vakcinacija.zajednicko.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "doza_osnova")
@XmlType(name = "TDoza", propOrder = {"brojDoze", "datumDavanja", "brojSerije"})
@Getter
@Setter
public class Doza extends RDFField {

    @XmlElement(name = "broj_doze", required = true)
    private RDFInteger brojDoze;

    @XmlElement(name = "datum_davanja", required = true)
    private RDFDate datumDavanja;

    @XmlElement(name = "broj_serije", required = true)
    private RDFString brojSerije;

    public Doza() {
    }

    public Doza(RDFInteger brojDoze, RDFDate datumDavanja, RDFString brojSerije) {
        this.brojDoze = brojDoze;
        this.datumDavanja = datumDavanja;
        this.brojSerije = brojSerije;
    }
}
