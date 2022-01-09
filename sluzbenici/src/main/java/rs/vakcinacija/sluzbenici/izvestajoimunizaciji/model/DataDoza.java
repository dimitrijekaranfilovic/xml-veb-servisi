package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model;

import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "data_doza")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TData_doza", propOrder = {"redniBroj", "brojDatihDoza"})
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

    public RDFField<Integer> getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(RDFField<Integer> redniBroj) {
        this.redniBroj = redniBroj;
    }

    public RDFField<Integer> getBrojDatihDoza() {
        return brojDatihDoza;
    }

    public void setBrojDatihDoza(RDFField<Integer> brojDatihDoza) {
        this.brojDatihDoza = brojDatihDoza;
    }
}
