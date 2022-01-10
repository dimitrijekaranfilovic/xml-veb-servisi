package rs.vakcinacija.zajednicko.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "doza_osnova")
@XmlType(name = "TDoza", propOrder = {"brojDoze", "datumDavanja", "brojSerije"})
@Getter
@Setter
public class Doza {

    @XmlElement(name = "broj_doze", required = true)
    private RDFField<Integer> brojDoze;

    @XmlElement(name = "datum_davanja", required = true)
    private RDFField<Date> datumDavanja;

    @XmlElement(name = "broj_serije", required = true)
    private RDFField<String> brojSerije;

    public Doza() {
    }

    public Doza(RDFField<Integer> brojDoze, RDFField<Date> datumDavanja, RDFField<String> brojSerije) {
        this.brojDoze = brojDoze;
        this.datumDavanja = datumDavanja;
        this.brojSerije = brojSerije;
    }
}
