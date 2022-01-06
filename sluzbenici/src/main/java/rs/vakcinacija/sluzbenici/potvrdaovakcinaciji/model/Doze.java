package rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model;


import rs.vakcinacija.zajednicko.model.Doza;

import javax.xml.bind.annotation.*;
import java.util.Collection;

@XmlRootElement(name = "doze")
@XmlType(name = "TDoze")
@XmlAccessorType(XmlAccessType.FIELD)
public class Doze {
    @XmlElement(name = "doza")
    private Collection<Doza> doze;


    public Doze(){
        super();
    }

    public Doze(Collection<Doza> doze) {
        this.doze = doze;
    }

    public Collection<Doza> getDoze() {
        return doze;
    }

    public void setDoze(Collection<Doza> doze) {
        this.doze = doze;
    }
}
