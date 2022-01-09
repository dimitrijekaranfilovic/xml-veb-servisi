package rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model;


import rs.vakcinacija.zajednicko.model.Doza;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Collection;

@XmlRootElement(name = "doze")
@XmlType(name = "TDoze")
@XmlAccessorType(XmlAccessType.FIELD)
public class Doze {
    @XmlElement(name = "doza")
    private RDFField<Collection<Doza>> doze;


    public Doze(){
        super();
    }

    public Doze(RDFField<Collection<Doza>> doze) {
        this.doze = doze;
    }

    public RDFField<Collection<Doza>> getDoze() {
        return doze;
    }

    public void setDoze(RDFField<Collection<Doza>> doze) {
        this.doze = doze;
    }
}
