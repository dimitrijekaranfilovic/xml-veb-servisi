package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model;


import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement(name = "izvestaj_o_imunizaciji")
@XmlAccessorType(XmlAccessType.FIELD)
public class IzvestajOImunizaciji {

    @XmlElement(name = "periodOd", required = true)
    @XmlSchemaType(name = "date")
    private Date periodOd;

    @XmlElement(name = "periodDo", required = true)
    @XmlSchemaType(name = "date")
    private Date periodDo;

    @XmlElement(name = "datum_izdavanja", required = true)
    @XmlSchemaType(name = "date")
    private Date datumIzdavanja;

    @XmlElement(name = "podneto_dokumenata_o_interesovanju", required = true)
    private Integer podnetoDokumenataOInteresovanju;

    @XmlElement(name = "primljeno_zahteva_za_sertifikat", required = true)
    private Integer primljenoZahtevaZaSertifikat;

    @XmlElement(name = "date_doze_vakcina", required = true)
    private DateDozeVakcina dateDozeVakcina;

    @XmlElement(name = "raspodela_po_proizvodjacima", required = true)
    private RaspodelaPoProizvodjacima raspodelaPoProizvodjacima;


    public IzvestajOImunizaciji() {
    }

    public IzvestajOImunizaciji(Date periodOD, Date periodDo,
                                Date datumIzdavanja,
                                Integer podnetoDokumenataOInteresovanju,
                                Integer primljenoZahtevaZaSertifikat,
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

    public Date getPeriodOd() {
        return periodOd;
    }

    public void setPeriodOd(Date periodOd) {
        this.periodOd = periodOd;
    }

    public Date getPeriodDo() {
        return periodDo;
    }

    public void setPeriodDo(Date periodDo) {
        this.periodDo = periodDo;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public Integer getPodnetoDokumenataOInteresovanju() {
        return podnetoDokumenataOInteresovanju;
    }

    public void setPodnetoDokumenataOInteresovanju(Integer podnetoDokumenataOInteresovanju) {
        this.podnetoDokumenataOInteresovanju = podnetoDokumenataOInteresovanju;
    }

    public Integer getPrimljenoZahtevaZaSertifikat() {
        return primljenoZahtevaZaSertifikat;
    }

    public void setPrimljenoZahtevaZaSertifikat(Integer primljenoZahtevaZaSertifikat) {
        this.primljenoZahtevaZaSertifikat = primljenoZahtevaZaSertifikat;
    }

    public DateDozeVakcina getDateDozeVakcina() {
        return dateDozeVakcina;
    }

    public void setDateDozeVakcina(DateDozeVakcina dateDozeVakcina) {
        this.dateDozeVakcina = dateDozeVakcina;
    }

    public RaspodelaPoProizvodjacima getRaspodelaPoProizvodjacima() {
        return raspodelaPoProizvodjacima;
    }

    public void setRaspodelaPoProizvodjacima(RaspodelaPoProizvodjacima raspodelaPoProizvodjacima) {
        this.raspodelaPoProizvodjacima = raspodelaPoProizvodjacima;
    }
}
