package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.model.RDFField;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "kontraindikacija")
@XmlType(name = "TKontraindikacija", propOrder = {"datumUtvrdjivanja", "dijagnoza"})
@Getter
@Setter
public class Kontraindikacija extends RDFField {

    @XmlElement(name = "datum_utvrdjivanja", required = true)
    private RDFDate datumUtvrdjivanja;

    @XmlElement(name = "dijagnoza", required = true)
    private RDFString dijagnoza;

    public Kontraindikacija() {}

    public Kontraindikacija(RDFDate datumUtvrdjivanja, RDFString dijagnoza) {
        this.datumUtvrdjivanja = datumUtvrdjivanja;
        this.dijagnoza = dijagnoza;
    }
}
