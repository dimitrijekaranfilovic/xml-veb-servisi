package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "data_doza")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TData_doza", propOrder = {"redniBroj", "brojDatihDoza"})
@Getter
@Setter
public class DataDoza {

    @XmlElement(name = "redni_broj", required = true)
    private RDFField<Integer> redniBroj;

    @XmlElement(name = "broj_datih_doza", required = true)
    private RDFField<Integer> brojDatihDoza;

    public DataDoza() {
        super();
    }

    public DataDoza(RDFField<Integer> redniBroj, RDFField<Integer> brojDatihDoza) {
        this.redniBroj = redniBroj;
        this.brojDatihDoza = brojDatihDoza;
    }
}
