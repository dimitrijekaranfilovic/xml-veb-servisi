package rs.vakcinacija.imunizacija.saglasnost.model;

import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Collection;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "privremene_kontraindikacije")
@XmlType(name = "TPrivremeneKontraindikacije", propOrder = {"kontraindikacije"})
public class PrivremeneKontraindikacije {

    @XmlElement(name="kontraindikacija", required=false)
    private RDFField<Collection<Kontraindikacija>> kontraindikacije;

    public PrivremeneKontraindikacije() {}

    public PrivremeneKontraindikacije(RDFField<Collection<Kontraindikacija>> kontraindikacije) {
        this.kontraindikacije = kontraindikacije;
    }

    public RDFField<Collection<Kontraindikacija>> getKontraindikacije() {
        return kontraindikacije;
    }

    public void setKontraindikacije(RDFField<Collection<Kontraindikacija>> kontraindikacije) {
        this.kontraindikacije = kontraindikacije;
    }
}
