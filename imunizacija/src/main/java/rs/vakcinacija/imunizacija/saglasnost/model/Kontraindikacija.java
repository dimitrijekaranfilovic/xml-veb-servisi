package rs.vakcinacija.imunizacija.saglasnost.model;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "kontraindikacija")
@XmlType(name = "TKontraindikacija", propOrder = {"datumUtvrdjivanja", "dijagnoza"})
public class Kontraindikacija {

    @XmlElement(name = "datum_utvrdjivanja", required = true)
    @XmlSchemaType(name = "date")
    private Date datumUtvrdjivanja;

    @XmlElement(name = "dijagnoza", required = true)
    private String dijagnoza;

    public Kontraindikacija() {}

    public Kontraindikacija(Date datumUtvrdjivanja, String dijagnoza) {
        this.datumUtvrdjivanja = datumUtvrdjivanja;
        this.dijagnoza = dijagnoza;
    }

    public Date getDatumUtvrdjivanja() {
        return datumUtvrdjivanja;
    }

    public void setDatumUtvrdjivanja(Date datumUtvrdjivanja) {
        this.datumUtvrdjivanja = datumUtvrdjivanja;
    }

    public String getDijagnoza() {
        return dijagnoza;
    }

    public void setDijagnoza(String dijagnoza) {
        this.dijagnoza = dijagnoza;
    }
}
