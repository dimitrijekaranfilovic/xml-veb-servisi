package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model;


import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement(name = "izvestaj_o_imunizaciji")
@XmlAccessorType(XmlAccessType.FIELD)
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

    public RDFField<Date> getPeriodOd() {
        return periodOd;
    }

    public void setPeriodOd(RDFField<Date> periodOd) {
        this.periodOd = periodOd;
    }

    public RDFField<Date> getPeriodDo() {
        return periodDo;
    }

    public void setPeriodDo(RDFField<Date> periodDo) {
        this.periodDo = periodDo;
    }

    public RDFField<Date> getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(RDFField<Date> datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public RDFField<Integer> getPodnetoDokumenataOInteresovanju() {
        return podnetoDokumenataOInteresovanju;
    }

    public void setPodnetoDokumenataOInteresovanju(RDFField<Integer> podnetoDokumenataOInteresovanju) {
        this.podnetoDokumenataOInteresovanju = podnetoDokumenataOInteresovanju;
    }

    public RDFField<Integer> getPrimljenoZahtevaZaSertifikat() {
        return primljenoZahtevaZaSertifikat;
    }

    public void setPrimljenoZahtevaZaSertifikat(RDFField<Integer> primljenoZahtevaZaSertifikat) {
        this.primljenoZahtevaZaSertifikat = primljenoZahtevaZaSertifikat;
    }

    public RDFField<DateDozeVakcina> getDateDozeVakcina() {
        return dateDozeVakcina;
    }

    public void setDateDozeVakcina(RDFField<DateDozeVakcina> dateDozeVakcina) {
        this.dateDozeVakcina = dateDozeVakcina;
    }

    public RDFField<RaspodelaPoProizvodjacima> getRaspodelaPoProizvodjacima() {
        return raspodelaPoProizvodjacima;
    }

    public void setRaspodelaPoProizvodjacima(RDFField<RaspodelaPoProizvodjacima> raspodelaPoProizvodjacima) {
        this.raspodelaPoProizvodjacima = raspodelaPoProizvodjacima;
    }
}
