package rs.vakcinacija.zajednicko.model;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "doza_osnova")
@XmlType(name = "TDoza", propOrder = {"brojDoze", "datumDavanja", "brojSerije"})
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

    public RDFField<Integer> getBrojDoze() {
        return brojDoze;
    }

    public void setBrojDoze(RDFField<Integer> brojDoze) {
        this.brojDoze = brojDoze;
    }

    public RDFField<Date> getDatumDavanja() {
        return datumDavanja;
    }

    public void setDatumDavanja(RDFField<Date> datumDavanja) {
        this.datumDavanja = datumDavanja;
    }

    public RDFField<String> getBrojSerije() {
        return brojSerije;
    }

    public void setBrojSerije(RDFField<String> brojSerije) {
        this.brojSerije = brojSerije;
    }
}
