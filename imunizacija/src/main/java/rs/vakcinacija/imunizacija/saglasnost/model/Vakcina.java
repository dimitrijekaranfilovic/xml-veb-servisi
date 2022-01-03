package rs.vakcinacija.imunizacija.saglasnost.model;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vakcina")
@XmlType(name = "TVakcina",
        propOrder = {"brojDoze", "datumDavanja", "brojSerije", "tip", "proizvodjac", "ekstremitet", "nuspojava"})
public class Vakcina {

    @XmlElement(name = "broj_doze", required = true, namespace = "https://www.vakcinacija.rs/zajednicko")
    private Integer brojDoze;

    @XmlElement(name = "datum_davanja", required = true, namespace = "https://www.vakcinacija.rs/zajednicko")
    @XmlSchemaType(name = "date")
    private Date datumDavanja;

    @XmlElement(name = "broj_serije", required = true, namespace = "https://www.vakcinacija.rs/zajednicko")
    private String brojSerije;

    @XmlElement(name = "tip", required = true, namespace = "https://www.vakcinacija.rs/zajednicko")
    private String tip;

    @XmlElement(name = "proizvodjac", required = true, namespace = "https://www.vakcinacija.rs/zajednicko")
    private String proizvodjac;

    @XmlElement(name = "ekstremitet", required = true)
    private String ekstremitet;

    @XmlElement(name = "nuspojava", required = true)
    private String nuspojava;

    public Vakcina() {}

    public Vakcina(Integer brojDoze,
                   Date datumDavanja,
                   String brojSerije,
                   String tip,
                   String proizvodjac,
                   String ekstremitet,
                   String nuspojava) {
        this.brojDoze = brojDoze;
        this.datumDavanja = datumDavanja;
        this.brojSerije = brojSerije;
        this.tip = tip;
        this.proizvodjac = proizvodjac;
        this.ekstremitet = ekstremitet;
        this.nuspojava = nuspojava;
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

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public String getEkstremitet() {
        return ekstremitet;
    }

    public void setEkstremitet(String ekstremitet) {
        this.ekstremitet = ekstremitet;
    }

    public String getNuspojava() {
        return nuspojava;
    }

    public void setNuspojava(String nuspojava) {
        this.nuspojava = nuspojava;
    }
}
