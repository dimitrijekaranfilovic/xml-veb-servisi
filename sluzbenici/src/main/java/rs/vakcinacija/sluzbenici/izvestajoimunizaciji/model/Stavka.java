package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model;


import javax.xml.bind.annotation.*;

@XmlRootElement(name = "stavka")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TStavka", propOrder = {"proizvodjac", "brojDoza"})
public class Stavka {

    @XmlElement(name = "proizvodjac", required = true)
    private String proizvodjac;

    @XmlElement(name = "broj_doza", required = true)
    private Integer brojDoza;


    public Stavka() {
    }

    public Stavka(String proizvodjac, Integer brojDoza) {
        this.proizvodjac = proizvodjac;
        this.brojDoza = brojDoza;
    }


    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public Integer getBrojDoza() {
        return brojDoza;
    }

    public void setBrojDoza(Integer brojDoza) {
        this.brojDoza = brojDoza;
    }
}
