package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model;


import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Collection;

@XmlRootElement(name = "raspodela_po_proizvodjacima")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TRaspodela_po_proizvodjacima")
@Getter
@Setter
public class RaspodelaPoProizvodjacima {

    @XmlElement(name = "stavka", required = true)
    private RDFField<Collection<Stavka>> stavke;

    public  RaspodelaPoProizvodjacima(){
        super();
    }

    public RaspodelaPoProizvodjacima(RDFField<Collection<Stavka>> stavke) {
        this.stavke = stavke;
    }
}
