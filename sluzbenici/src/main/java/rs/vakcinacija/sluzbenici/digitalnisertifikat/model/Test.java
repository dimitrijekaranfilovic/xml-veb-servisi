package rs.vakcinacija.sluzbenici.digitalnisertifikat.model;

import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.model.RDFField;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "test")
@XmlType(name = "TTest", propOrder = {"naziv", "opis", "vrstaUzorka", "proizvodjacTesta", "datumIVremeUzorkovanja", "datumIVremeRezultata", "rezultat", "laboratorija"})
@Getter
@Setter
public class Test extends RDFField {

    @XmlElement(name = "naziv", required = true)
    private RDFString naziv;

    @XmlElement(name = "opis", required = true)
    private RDFString opis;

    @XmlElement(name = "vrsta_uzorka", required = true)
    private RDFString vrstaUzorka;

    @XmlElement(name = "proizvodjac_testa", required = true)
    private RDFString proizvodjacTesta;

    @XmlElement(name = "datum_i_vreme_uzorkovanja", required = true)
    private RDFDate datumIVremeUzorkovanja;

    @XmlElement(name = "datum_i_vreme_rezultata", required = true)
    private RDFDate datumIVremeRezultata;

    @XmlElement(name = "rezultat", required = true)
    private RDFString rezultat;

    @XmlElement(name = "laboratorija", required = true)
    private RDFString laboratorija;

    public Test() {
    }

    public Test(RDFString naziv, RDFString opis, RDFString vrstaUzorka, RDFString proizvodjacTesta,
                RDFDate datumIVremeUzorkovanja, RDFDate datumIVremeRezultata, RDFString rezultat, RDFString laboratorija) {
        this.naziv = naziv;
        this.opis = opis;
        this.vrstaUzorka = vrstaUzorka;
        this.proizvodjacTesta = proizvodjacTesta;
        this.datumIVremeUzorkovanja = datumIVremeUzorkovanja;
        this.datumIVremeRezultata = datumIVremeRezultata;
        this.rezultat = rezultat;
        this.laboratorija = laboratorija;
    }
}
