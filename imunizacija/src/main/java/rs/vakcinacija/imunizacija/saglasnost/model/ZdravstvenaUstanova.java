package rs.vakcinacija.imunizacija.saglasnost.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "zdravstvena_ustanova")
@XmlType(name = "TZdravstvenaUstanova", propOrder = {"naziv", "punkt"})
public class ZdravstvenaUstanova {

    @XmlElement(name = "naziv", required = true)
    private String naziv;

    @XmlElement(name = "punkt", required = true)
    private Integer punkt;

    public ZdravstvenaUstanova() {}

    public ZdravstvenaUstanova(String naziv, Integer punkt) {
        this.naziv = naziv;
        this.punkt = punkt;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getPunkt() {
        return punkt;
    }

    public void setPunkt(Integer punkt) {
        this.punkt = punkt;
    }
}
