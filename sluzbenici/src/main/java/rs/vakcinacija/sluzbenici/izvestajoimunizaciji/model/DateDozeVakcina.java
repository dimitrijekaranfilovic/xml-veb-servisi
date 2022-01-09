package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model;


import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Collection;

@XmlRootElement(name = "date_doze")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TDate_doze_vakcina")
public class DateDozeVakcina {

    @XmlElement(name = "data_doza")
    private RDFField<Collection<DataDoza>> dateDoze;


    public DateDozeVakcina() {
        super();
    }

    public DateDozeVakcina(RDFField<Collection<DataDoza>> dateDoze) {
        this.dateDoze = dateDoze;
    }

    public RDFField<Collection<DataDoza>> getDateDoze() {
        return dateDoze;
    }

    public void setDateDoze(RDFField<Collection<DataDoza>> dateDoze) {
        this.dateDoze = dateDoze;
    }
}
