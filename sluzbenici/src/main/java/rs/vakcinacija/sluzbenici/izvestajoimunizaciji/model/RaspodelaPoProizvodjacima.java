package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.Collection;

@XmlRootElement(name = "raspodela_po_proizvodjacima")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TRaspodela_po_proizvodjacima")
@Getter
@Setter
public class RaspodelaPoProizvodjacima {

    @XmlElement(name = "stavka", required = true)
    private Collection<Stavka> stavke;

    public  RaspodelaPoProizvodjacima(){
        super();
    }

    public RaspodelaPoProizvodjacima(Collection<Stavka> stavke) {
        this.stavke = stavke;
    }
}
