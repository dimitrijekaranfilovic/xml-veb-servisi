package rs.vakcinacija.imunizacija.digitalnisertifikat.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Collection;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "testovi")
@XmlType(name = "TTestovi", propOrder = {"testovi"})
@Getter
@Setter
public class Testovi extends RDFField {

    @XmlElement(name = "test")
    private Collection<Test> testovi;

    public Testovi() {
    }

    public Testovi(Collection<Test> testovi) {
        this.testovi = testovi;
    }
}
