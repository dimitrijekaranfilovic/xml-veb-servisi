package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model;


import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFInteger;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "stavka")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TStavka", propOrder = {"proizvodjac", "brojDoza"})
@Getter
@Setter
public class Stavka {

    @XmlElement(name = "proizvodjac", required = true)
    private RDFString proizvodjac;

    @XmlElement(name = "broj_doza", required = true)
    private RDFInteger brojDoza;


    public Stavka() {
    }

    public Stavka(RDFString proizvodjac, RDFInteger brojDoza) {
        this.proizvodjac = proizvodjac;
        this.brojDoza = brojDoza;
    }
}
