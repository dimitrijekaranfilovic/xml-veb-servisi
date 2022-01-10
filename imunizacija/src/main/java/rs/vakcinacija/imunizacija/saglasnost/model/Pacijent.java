package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "pacijent")
@XmlType(name = "TPacijent", propOrder = {"licneInformacije", "saglasnost"})
@Getter
@Setter
public class Pacijent extends RDFField {

    @XmlElement(name = "licne_informacije", required = true)
    private LicneInformacije licneInformacije;

    @XmlElement(name = "saglasnost", required = true)
    private Saglasnost saglasnost;

    public Pacijent() {}

    public Pacijent(LicneInformacije licneInformacije, Saglasnost saglasnost) {
        this.licneInformacije = licneInformacije;
        this.saglasnost = saglasnost;
    }
}
