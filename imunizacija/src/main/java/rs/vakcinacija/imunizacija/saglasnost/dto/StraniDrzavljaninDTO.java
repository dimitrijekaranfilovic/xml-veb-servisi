package rs.vakcinacija.imunizacija.saglasnost.dto;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "straniDrzavljanin")
@XmlType(name = "TStraniDrzavljanin", propOrder = {"nazivDrzavljanstva", "brojPasosa"})
@Getter
@Setter
public class StraniDrzavljaninDTO {

    @XmlElement(name = "nazivDrzavljanstva", required = true)
    private RDFString nazivDrzavljanstva;

    @XmlElement(name = "brojPasosa", required = true)
    private RDFString brojPasosa;

    public StraniDrzavljaninDTO() {}

    public StraniDrzavljaninDTO(RDFString nazivDrzavljanstva, RDFString brojPasosa) {
        this.nazivDrzavljanstva = nazivDrzavljanstva;
        this.brojPasosa = brojPasosa;
    }
}
