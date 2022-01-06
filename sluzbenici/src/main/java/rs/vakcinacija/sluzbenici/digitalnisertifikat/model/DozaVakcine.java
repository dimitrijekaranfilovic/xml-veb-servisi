package rs.vakcinacija.sluzbenici.digitalnisertifikat.model;


import rs.vakcinacija.zajednicko.model.DozaDetaljnije;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "doza_vakcine")
@XmlType(name = "TDoza_vakcine", propOrder = {"zdravstvenaUstanova"})
public class DozaVakcine extends DozaDetaljnije {

    @XmlElement(name = "zdravstvena_ustanova", required = true)
    private String zdravstvenaUstanova;

    public DozaVakcine() {
    }

    public DozaVakcine(Integer brojDoze, Date datumDavanja, String brojSerije, String tip,
                       String proizvodjac, String zdravstvenaUstanova) {
        super(brojDoze, datumDavanja, brojSerije, tip, proizvodjac);
        this.zdravstvenaUstanova = zdravstvenaUstanova;
    }

    public String getZdravstvenaUstanova() {
        return zdravstvenaUstanova;
    }

    public void setZdravstvenaUstanova(String zdravstvenaUstanova) {
        this.zdravstvenaUstanova = zdravstvenaUstanova;
    }
}
