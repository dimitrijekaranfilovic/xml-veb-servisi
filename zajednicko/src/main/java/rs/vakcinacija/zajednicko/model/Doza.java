package rs.vakcinacija.zajednicko.model;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "doza_osnova")
@XmlType(name = "TDoza", propOrder = {"brojDoze", "datumDavanja", "brojSerije"})
public class Doza {

    @XmlElement(name = "broj_doze", required = true)
    private Integer brojDoze;

    @XmlElement(name = "datum_davanja", required = true)
    private Date datumDavanja;

    @XmlElement(name = "broj_serije", required = true)
    private String brojSerije;

    public Doza() {
    }

    public Doza(Integer brojDoze, Date datumDavanja, String brojSerije) {
        this.brojDoze = brojDoze;
        this.datumDavanja = datumDavanja;
        this.brojSerije = brojSerije;
    }

    public Integer getBrojDoze() {
        return brojDoze;
    }

    public void setBrojDoze(Integer brojDoze) {
        this.brojDoze = brojDoze;
    }

    public Date getDatumDavanja() {
        return datumDavanja;
    }

    public void setDatumDavanja(Date datumDavanja) {
        this.datumDavanja = datumDavanja;
    }

    public String getBrojSerije() {
        return brojSerije;
    }

    public void setBrojSerije(String brojSerije) {
        this.brojSerije = brojSerije;
    }
}
