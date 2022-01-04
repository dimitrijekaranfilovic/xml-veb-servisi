package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model;


import javax.xml.bind.annotation.*;
import java.util.Collection;

@XmlRootElement(name = "date_doze")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TDate_doze_vakcina")
public class DateDozeVakcina {

    @XmlElement(name = "data_doza")
    private Collection<DataDoza> dateDoze;


    public DateDozeVakcina() {
        super();
    }

    public DateDozeVakcina(Collection<DataDoza> dateDoze) {
        this.dateDoze = dateDoze;
    }

    public Collection<DataDoza> getDateDoze() {
        return dateDoze;
    }

    public void setDateDoze(Collection<DataDoza> dateDoze) {
        this.dateDoze = dateDoze;
    }
}
