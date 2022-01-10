package rs.vakcinacija.imunizacija.saglasnost.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vakcinacija")
@XmlType(name = "TVakcinacija",
        propOrder = {"zdravstvenaUstanova", "lekar", "vakcine", "privremeneKontraindikacije", "odlukaKomisije"})
@Getter
@Setter
public class Vakcinacija {

    @XmlElement(name = "zdravstvena_ustanova", required = true)
    private RDFField<ZdravstvenaUstanova> zdravstvenaUstanova;

    @XmlElement(name = "lekar", required = true)
    private RDFField<Lekar> lekar;

    @XmlElement(name = "vakcine", required = true)
    private RDFField<Vakcine> vakcine;

    @XmlElement(name = "privremene_kontraindikacije", required = false)
    private RDFField<PrivremeneKontraindikacije> privremeneKontraindikacije;

    @XmlElement(name = "odluka_komisije_za_trajne_kontraindikacije", required = true)
    private RDFField<Boolean> odlukaKomisije;

    public Vakcinacija() {
    }

    public Vakcinacija(RDFField<ZdravstvenaUstanova> zdravstvenaUstanova, RDFField<Lekar> lekar,
                       RDFField<Vakcine> vakcine, RDFField<PrivremeneKontraindikacije> privremeneKontraindikacije,
                       RDFField<Boolean> odlukaKomisije) {
        this.zdravstvenaUstanova = zdravstvenaUstanova;
        this.lekar = lekar;
        this.vakcine = vakcine;
        this.privremeneKontraindikacije = privremeneKontraindikacije;
        this.odlukaKomisije = odlukaKomisije;
    }
}
