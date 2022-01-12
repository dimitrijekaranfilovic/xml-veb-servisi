package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model;


import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.model.RDFInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "izvestaj_o_imunizaciji")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class IzvestajOImunizaciji {

    @XmlElement(name = "periodOd", required = true)
    private RDFDate periodOd;

    @XmlElement(name = "periodDo", required = true)
    private RDFDate periodDo;

    @XmlElement(name = "datum_izdavanja", required = true)
    private RDFDate datumIzdavanja;

    @XmlElement(name = "podneto_dokumenata_o_interesovanju", required = true)
    private RDFInteger podnetoDokumenataOInteresovanju;

    @XmlElement(name = "primljeno_zahteva_za_sertifikat", required = true)
    private RDFInteger primljenoZahtevaZaSertifikat;

    @XmlElement(name = "izdato_zahteva_za_sertifikat", required = true)
    private RDFInteger izdatoZahtevaZaSertifikat;

    @XmlElement(name = "date_doze_vakcina", required = true)
    private DateDozeVakcina dateDozeVakcina;

    @XmlElement(name = "raspodela_po_proizvodjacima", required = true)
    private RaspodelaPoProizvodjacima raspodelaPoProizvodjacima;


    public IzvestajOImunizaciji() {
    }

    public IzvestajOImunizaciji(RDFDate periodOD,
                                RDFDate periodDo,
                                RDFDate datumIzdavanja,
                                RDFInteger podnetoDokumenataOInteresovanju,
                                RDFInteger primljenoZahtevaZaSertifikat,
                                DateDozeVakcina dateDozeVakcina,
                                RaspodelaPoProizvodjacima raspodelaPoProizvodjacima) {
        this.periodOd = periodOD;
        this.periodDo = periodDo;
        this.datumIzdavanja = datumIzdavanja;
        this.podnetoDokumenataOInteresovanju = podnetoDokumenataOInteresovanju;
        this.primljenoZahtevaZaSertifikat = primljenoZahtevaZaSertifikat;
        this.dateDozeVakcina = dateDozeVakcina;
        this.raspodelaPoProizvodjacima = raspodelaPoProizvodjacima;
    }
}
