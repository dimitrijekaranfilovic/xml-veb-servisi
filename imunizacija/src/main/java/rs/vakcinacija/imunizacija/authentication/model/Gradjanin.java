package rs.vakcinacija.imunizacija.authentication.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFBoolean;
import rs.vakcinacija.zajednicko.model.RDFField;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "gradjanin")
@Getter
@Setter
public class Gradjanin extends RDFField {

    @XmlElement(name = "ime", required = true)
    private RDFString name;

    @XmlElement(name = "prezime", required = true)
    private RDFString surname;

    @XmlElement(name = "email", required = true)
    private RDFString email;

    @XmlElement(name = "password", required = true)
    private RDFString password;

    @XmlElement(name = "dao_saglasnost", required = true)
    private RDFBoolean agreedOnVaccination;

    public Gradjanin() {}

    public Gradjanin(RDFString email, RDFString password, RDFString name, RDFString surname) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.agreedOnVaccination.setValue(false);
    }
}
