package rs.vakcinacija.sluzbenici.digitalnisertifikat.model;

import javax.xml.bind.annotation.*;
import java.util.Collection;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "testovi")
@XmlType(name = "TTestovi", propOrder = {"testovi"})
public class Testovi {

    @XmlElement(name = "test")
    private Collection<Test> testovi;

}
