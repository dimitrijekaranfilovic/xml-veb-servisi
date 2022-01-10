package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model;


import lombok.Getter;
import lombok.Setter;
import rs.vakcinacija.zajednicko.model.RDFField;

import javax.xml.bind.annotation.*;
import java.util.Collection;

@XmlRootElement(name = "date_doze")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TDate_doze_vakcina")
@Getter
@Setter
public class DateDozeVakcina extends RDFField {

    @XmlElement(name = "data_doza")
    private Collection<DataDoza> dateDoze;


    public DateDozeVakcina() {
        super();
    }

    public DateDozeVakcina(Collection<DataDoza> dateDoze) {
        this.dateDoze = dateDoze;
    }
}
