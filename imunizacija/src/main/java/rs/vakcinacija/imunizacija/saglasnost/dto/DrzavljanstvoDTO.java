package rs.vakcinacija.imunizacija.saglasnost.dto;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "drzavljanstvo")
@XmlType(name = "TDrzavljanstvo", propOrder = {"srpskiDrzavljanin", "straniDrzavljanin"})
@Getter
@Setter
public class DrzavljanstvoDTO {

    @XmlElement(name = "srpskiDrzavljanin", required = false)
    private SrpskiDrzavljaninDTO srpskiDrzavljanin;

    @XmlElement(name = "straniDrzavljanin", required = false)
    private StraniDrzavljaninDTO straniDrzavljanin;

    public DrzavljanstvoDTO() {}

    public DrzavljanstvoDTO(SrpskiDrzavljaninDTO srpskiDrzavljanin, StraniDrzavljaninDTO straniDrzavljanin) {
        this.srpskiDrzavljanin = srpskiDrzavljanin;
        this.straniDrzavljanin = straniDrzavljanin;
    }
}
