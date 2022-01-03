package rs.vakcinacija.imunizacija.saglasnost.model;

import javax.xml.bind.annotation.*;
import java.util.Collection;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "privremene_kontraindikacije")
@XmlType(name = "TPrivremeneKontraindikacije", propOrder = {"kontraindikacije"})
public class PrivremeneKontraindikacije {

    @XmlElement(name="kontraindikacija", required=false)
    private Collection<Kontraindikacija> kontraindikacije;

    public PrivremeneKontraindikacije() {}

    public PrivremeneKontraindikacije(Collection<Kontraindikacija> kontraindikacije) {
        this.kontraindikacije = kontraindikacije;
    }

    public Collection<Kontraindikacija> getKontraindikacije() {
        return kontraindikacije;
    }

    public void setKontraindikacije(Collection<Kontraindikacija> kontraindikacije) {
        this.kontraindikacije = kontraindikacije;
    }
}
