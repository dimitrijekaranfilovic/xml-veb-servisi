package rs.vakcinacija.sluzbenici.digitalnisertifikat.model;

import javax.xml.bind.annotation.*;
import java.util.Collection;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vakcinacija")
@XmlType(name = "TVakcinacija", propOrder = {"vakcine"})
public class Vakcinacija {

    @XmlElement(name = "doza", required = true)
    Collection<Doza> vakcine;

}
