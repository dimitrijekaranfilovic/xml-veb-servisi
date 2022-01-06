package rs.vakcinacija.zajednicko.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "kontakt")
@XmlType(name = "TKontakt", propOrder = {"brojFiksnog", "brojMobilnog", "email"})
public class Kontakt {

    @XmlElement(name = "broj_fiksnog", required = true)
    private String brojFiksnog;

    @XmlElement(name = "broj_mobilnog", required = true)
    private String brojMobilnog;

    @XmlElement(name = "email", required = true)
    private String email;


    public Kontakt() {
    }

    public Kontakt(String brojFiksnog, String brojMobilnog, String email) {
        this.brojFiksnog = brojFiksnog;
        this.brojMobilnog = brojMobilnog;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}