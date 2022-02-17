package rs.vakcinacija.imunizacija.digitalnisertifikat.model;


import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.DozaDetaljnije;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.model.RDFInteger;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "doza_vakcine")
@XmlType(name = "TDoza_vakcine", propOrder = {"zdravstvenaUstanova"})
@Getter
@Setter
public class DozaVakcine extends DozaDetaljnije {

    @XmlElement(name = "zdravstvena_ustanova", required = true)
    private RDFString zdravstvenaUstanova;

    public DozaVakcine() {
    }

    public DozaVakcine(RDFInteger brojDoze, RDFDate datumDavanja, RDFString brojSerije,
                       RDFString tip, RDFString proizvodjac, RDFString zdravstvenaUstanova) {
        super(brojDoze, datumDavanja, brojSerije, tip, proizvodjac);
        this.zdravstvenaUstanova = zdravstvenaUstanova;
    }
}
