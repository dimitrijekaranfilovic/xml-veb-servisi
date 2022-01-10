package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model;


import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "stavka")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TStavka", propOrder = {"proizvodjac", "brojDoza"})
@Getter
@Setter
public class Stavka {

    @XmlElement(name = "proizvodjac", required = true)
    private RDFField<String> proizvodjac;

    @XmlElement(name = "broj_doza", required = true)
    private RDFField<Integer> brojDoza;


    public Stavka() {
    }

    public Stavka(RDFField<String> proizvodjac, RDFField<Integer> brojDoza) {
        this.proizvodjac = proizvodjac;
        this.brojDoza = brojDoza;
    }
}
