package rs.vakcinacija.sluzbenici.digitalnisertifikat.model;


import rs.vakcinacija.zajednicko.model.DozaDetaljnije;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "doza_vakcine")
@XmlType(name = "TDoza_vakcine", propOrder = {"zdravstvenaUstanova"})
public class DozaVakcine extends DozaDetaljnije {

    @XmlElement(name = "zdravstvena_ustanova", required = true)
    private RDFField<String> zdravstvenaUstanova;

    public DozaVakcine() {
    }

    public DozaVakcine(Integer brojDoze, Date datumDavanja, String brojSerije, String tip, String proizvodjac, RDFField<String> zdravstvenaUstanova) {
        super(brojDoze, datumDavanja, brojSerije, tip, proizvodjac);
        this.zdravstvenaUstanova = zdravstvenaUstanova;
    }
}
