package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "data_doza")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TData_doza", propOrder = {"redniBroj", "brojDatihDoza"})
public class DataDoza {

    @XmlElement(name = "redni_broj", required = true)
    private Integer redniBroj;

    @XmlElement(name = "broj_datih_doza", required = true)
    private Integer brojDatihDoza;

    public DataDoza() {
        super();
    }

    public DataDoza(Integer redniBroj, Integer brojDatihDoza) {
        this.redniBroj = redniBroj;
        this.brojDatihDoza = brojDatihDoza;
    }

    public Integer getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(Integer redniBroj) {
        this.redniBroj = redniBroj;
    }

    public Integer getBrojDatihDoza() {
        return brojDatihDoza;
    }

    public void setBrojDatihDoza(Integer brojDatihDoza) {
        this.brojDatihDoza = brojDatihDoza;
    }
}
