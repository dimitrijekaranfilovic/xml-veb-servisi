package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "kontraindikacija")
@XmlType(name = "TKontraindikacija", propOrder = {"datumUtvrdjivanja", "dijagnoza"})
@Getter
@Setter
public class Kontraindikacija {

    @XmlElement(name = "datum_utvrdjivanja", required = true)
    private RDFField<Date> datumUtvrdjivanja;

    @XmlElement(name = "dijagnoza", required = true)
    private RDFField<String> dijagnoza;

    public Kontraindikacija() {}

    public Kontraindikacija(RDFField<Date> datumUtvrdjivanja, RDFField<String> dijagnoza) {
        this.datumUtvrdjivanja = datumUtvrdjivanja;
        this.dijagnoza = dijagnoza;
    }
}
