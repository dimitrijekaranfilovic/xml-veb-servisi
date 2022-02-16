package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFBoolean;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vakcinacija")
@XmlType(name = "TVakcinacija",
        propOrder = {"zdravstvenaUstanova", "lekar", "vakcine", "privremeneKontraindikacije", "odlukaKomisije"})
@Getter
@Setter
public class Vakcinacija extends RDFField {

    @XmlElement(name = "zdravstvena_ustanova", required = true)
    private ZdravstvenaUstanova zdravstvenaUstanova;

    @XmlElement(name = "lekar", required = true)
    private Lekar lekar;

    @XmlElement(name = "vakcine", required = true)
    private Vakcine vakcine;

    @XmlElement(name = "privremene_kontraindikacije")
    private PrivremeneKontraindikacije privremeneKontraindikacije;

    @XmlElement(name = "odluka_komisije_za_trajne_kontraindikacije", required = true)
    private RDFBoolean odlukaKomisije;

    public Vakcinacija() {
    }

    public Vakcinacija(ZdravstvenaUstanova zdravstvenaUstanova, Lekar lekar,
                       Vakcine vakcine, PrivremeneKontraindikacije privremeneKontraindikacije,
                       RDFBoolean odlukaKomisije) {
        this.zdravstvenaUstanova = zdravstvenaUstanova;
        this.lekar = lekar;
        this.vakcine = vakcine;
        this.privremeneKontraindikacije = privremeneKontraindikacije;
        this.odlukaKomisije = odlukaKomisije;
    }
}
