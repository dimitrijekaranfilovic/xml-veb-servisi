package rs.vakcinacija.imunizacija.saglasnost.dto;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "srpskiDrzavljanin")
@XmlType(name = "TSrpskiDrzavljanin", propOrder = {"jmbg"})
@Getter
@Setter
public class SrpskiDrzavljaninDTO {

    @XmlElement(name = "jmbg", required = true)
    private RDFString jmbg;

    public SrpskiDrzavljaninDTO() {}

    public SrpskiDrzavljaninDTO(RDFString jmbg) {
        this.jmbg = jmbg;
    }
}
