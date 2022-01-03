package rs.vakcinacija.imunizacija.saglasnost.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "telefon")
@XmlType(name = "TTelefon", propOrder = {"brojFiksnog", "brojMobilnog"})
public class Telefon {

    @XmlElement(name = "broj_fiksnog", required = true)
    private String brojFiksnog;

    @XmlElement(name = "broj_mobilnog", required = true)
    private String brojMobilnog;

    public Telefon() {}

    public Telefon(String brojFiksnog, String brojMobilnog) {
        this.brojFiksnog = brojFiksnog;
        this.brojMobilnog = brojMobilnog;
    }

    public String getBrojFiksnog() {
        return brojFiksnog;
    }

    public void setBrojFiksnog(String brojFiksnog) {
        this.brojFiksnog = brojFiksnog;
    }

    public String getBrojMobilnog() {
        return brojMobilnog;
    }

    public void setBrojMobilnog(String brojMobilnog) {
        this.brojMobilnog = brojMobilnog;
    }
}
