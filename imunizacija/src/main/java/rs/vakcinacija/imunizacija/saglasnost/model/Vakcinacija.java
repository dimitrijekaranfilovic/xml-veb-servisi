package rs.vakcinacija.imunizacija.saglasnost.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vakcinacija")
@XmlType(name = "TVakcinacija",
        propOrder = {"zdravstvenaUstanova", "lekar", "vakcine", "privremeneKontraindikacije", "odlukaKomisije"})
public class Vakcinacija {

    @XmlElement(name = "zdravstvena_ustanova", required = true)
    private ZdravstvenaUstanova zdravstvenaUstanova;

    @XmlElement(name = "lekar", required = true)
    private Lekar lekar;

    @XmlElement(name = "vakcine", required = true)
    private Vakcine vakcine;

    @XmlElement(name = "privremene_kontraindikacije", required = false)
    private PrivremeneKontraindikacije privremeneKontraindikacije;

    @XmlElement(name = "odluka_komisije_za_trajne_kontraindikacije", required = true)
    private Boolean odlukaKomisije;

    public Vakcinacija() {
    }

    public Vakcinacija(ZdravstvenaUstanova zdravstvenaUstanova,
                       Lekar lekar,
                       Vakcine vakcine,
                       PrivremeneKontraindikacije privremeneKontraindikacije,
                       Boolean odlukaKomisije) {
        this.zdravstvenaUstanova = zdravstvenaUstanova;
        this.lekar = lekar;
        this.vakcine = vakcine;
        this.privremeneKontraindikacije = privremeneKontraindikacije;
        this.odlukaKomisije = odlukaKomisije;
    }

    public ZdravstvenaUstanova getZdravstvenaUstanova() {
        return zdravstvenaUstanova;
    }

    public void setZdravstvenaUstanova(ZdravstvenaUstanova zdravstvenaUstanova) {
        this.zdravstvenaUstanova = zdravstvenaUstanova;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    public Vakcine getVakcine() {
        return vakcine;
    }

    public void setVakcine(Vakcine vakcine) {
        this.vakcine = vakcine;
    }

    public PrivremeneKontraindikacije getPrivremeneKontraindikacije() {
        return privremeneKontraindikacije;
    }

    public void setPrivremeneKontraindikacije(PrivremeneKontraindikacije privremeneKontraindikacije) {
        this.privremeneKontraindikacije = privremeneKontraindikacije;
    }

    public Boolean getOdlukaKomisije() {
        return odlukaKomisije;
    }

    public void setOdlukaKomisije(Boolean odlukaKomisije) {
        this.odlukaKomisije = odlukaKomisije;
    }
}
