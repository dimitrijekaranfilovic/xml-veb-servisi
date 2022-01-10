package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;
import rs.vakcinacija.zajednicko.model.RDFInteger;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "data_doza")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TData_doza", propOrder = {"redniBroj", "brojDatihDoza"})
@Getter
@Setter
public class DataDoza extends RDFField {

    @XmlElement(name = "redni_broj", required = true)
    private RDFInteger redniBroj;

    @XmlElement(name = "broj_datih_doza", required = true)
    private RDFInteger brojDatihDoza;

    public DataDoza() {
        super();
    }

    public DataDoza(RDFInteger redniBroj, RDFInteger brojDatihDoza) {
        this.redniBroj = redniBroj;
        this.brojDatihDoza = brojDatihDoza;
    }
}
