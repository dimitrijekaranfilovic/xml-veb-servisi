package rs.vakcinacija.sluzbenici.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Collection;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "privremene_kontraindikacije")
@XmlType(name = "TPrivremeneKontraindikacije", propOrder = {"kontraindikacije"})
@Getter
@Setter
public class PrivremeneKontraindikacije extends RDFField {

    @XmlElement(name="kontraindikacija", required=false)
    private Collection<Kontraindikacija> kontraindikacije;

    public PrivremeneKontraindikacije() {}

    public PrivremeneKontraindikacije(Collection<Kontraindikacija> kontraindikacije) {
        this.kontraindikacije = kontraindikacije;
    }
}
