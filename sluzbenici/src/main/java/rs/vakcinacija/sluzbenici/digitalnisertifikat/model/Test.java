package rs.vakcinacija.sluzbenici.digitalnisertifikat.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "test")
@XmlType(name = "TTest", propOrder = {"naziv", "opis", "vrstaUzorka", "proizvodjacTesta", "datumIVremeUzorkovanja", "datumIVremeRezultata", "rezultat", "laboratorija"})
@Getter
@Setter
public class Test {

    @XmlElement(name = "naziv", required = true)
    private String naziv;

    @XmlElement(name = "opis", required = true)
    private String opis;

    @XmlElement(name = "vrsta_uzorka", required = true)
    private String vrstaUzorka;

    @XmlElement(name = "proizvodjac_testa", required = true)
    private String proizvodjacTesta;

    @XmlElement(name = "datum_i_vreme_uzorkovanja", required = true)
    @XmlSchemaType(name = "date")
    private Date datumIVremeUzorkovanja;

    @XmlElement(name = "datum_i_vreme_rezultata", required = true)
    @XmlSchemaType(name = "date")
    private Date datumIVremeRezultata;

    @XmlElement(name = "rezultat", required = true)
    private String rezultat;

    @XmlElement(name = "laboratorija", required = true)
    private String laboratorija;

    public Test() {
    }

    public Test(String naziv, String opis, String vrstaUzorka, String proizvodjacTesta, Date datumIVremeUzorkovanja, Date datumIVremeRezultata, String rezultat, String laboratorija) {
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
