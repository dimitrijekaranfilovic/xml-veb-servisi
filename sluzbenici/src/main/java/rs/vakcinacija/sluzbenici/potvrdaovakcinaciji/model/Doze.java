package rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model;


import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.Doza;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Collection;

@XmlRootElement(name = "doze")
@XmlType(name = "TDoze")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Doze extends RDFField {
    @XmlElement(name = "doza")
    private Collection<Doza> doze;


    public Doze(){
        super();
    }

    public Doze(Collection<Doza> doze) {
        this.doze = doze;
    }
}
