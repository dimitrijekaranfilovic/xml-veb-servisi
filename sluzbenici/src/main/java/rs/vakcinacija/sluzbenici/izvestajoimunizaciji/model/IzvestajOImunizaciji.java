package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model;


import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement(name = "izvestaj_o_imunizaciji")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class IzvestajOImunizaciji {

    @XmlElement(name = "periodOd", required = true)
    private RDFField<Date> periodOd;

    @XmlElement(name = "periodDo", required = true)
    private RDFField<Date> periodDo;

    @XmlElement(name = "datum_izdavanja", required = true)
    private RDFField<Date> datumIzdavanja;

    @XmlElement(name = "podneto_dokumenata_o_interesovanju", required = true)
    private RDFField<Integer> podnetoDokumenataOInteresovanju;

    @XmlElement(name = "primljeno_zahteva_za_sertifikat", required = true)
    private RDFField<Integer> primljenoZahtevaZaSertifikat;

    @XmlElement(name = "date_doze_vakcina", required = true)
    private RDFField<DateDozeVakcina> dateDozeVakcina;

    @XmlElement(name = "raspodela_po_proizvodjacima", required = true)
    private RDFField<RaspodelaPoProizvodjacima> raspodelaPoProizvodjacima;


    public IzvestajOImunizaciji() {
    }

    public IzvestajOImunizaciji(RDFField<Date> periodOD,
                                RDFField<Date> periodDo,
                                RDFField<Date> datumIzdavanja,
                                RDFField<Integer> podnetoDokumenataOInteresovanju,
                                RDFField<Integer> primljenoZahtevaZaSertifikat,
                                RDFField<DateDozeVakcina> dateDozeVakcina,
                                RDFField<RaspodelaPoProizvodjacima> raspodelaPoProizvodjacima) {
        this.periodOd = periodOD;
        this.periodDo = periodDo;
        this.datumIzdavanja = datumIzdavanja;
        this.podnetoDokumenataOInteresovanju = podnetoDokumenataOInteresovanju;
        this.primljenoZahtevaZaSertifikat = primljenoZahtevaZaSertifikat;
        this.dateDozeVakcina = dateDozeVakcina;
        this.raspodelaPoProizvodjacima = raspodelaPoProizvodjacima;
    }
}
