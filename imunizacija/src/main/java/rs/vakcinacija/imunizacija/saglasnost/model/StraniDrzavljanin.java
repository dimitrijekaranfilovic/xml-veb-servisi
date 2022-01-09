package rs.vakcinacija.imunizacija.saglasnost.model;

import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "strani_drzavljanin")
@XmlType(name = "TStraniDrzavljanin", propOrder = {"nazivDrzave", "brojPasosa"})
public class StraniDrzavljanin {

    @XmlElement(name = "naziv_drzave", required = true)
    private RDFField<String> nazivDrzave;

    @XmlElement(name = "broj_pasosa", required = true)
    private RDFField<String> brojPasosa;

    public StraniDrzavljanin() {}

    public StraniDrzavljanin(RDFField<String> nazivDrzave, RDFField<String> brojPasosa) {
        this.nazivDrzave = nazivDrzave;
        this.brojPasosa = brojPasosa;
    }

    public RDFField<String> getNazivDrzave() {
        return nazivDrzave;
    }

    public void setNazivDrzave(RDFField<String> nazivDrzave) {
        this.nazivDrzave = nazivDrzave;
    }

    public RDFField<String> getBrojPasosa() {
        return brojPasosa;
    }

    public void setBrojPasosa(RDFField<String> brojPasosa) {
        this.brojPasosa = brojPasosa;
    }
}
