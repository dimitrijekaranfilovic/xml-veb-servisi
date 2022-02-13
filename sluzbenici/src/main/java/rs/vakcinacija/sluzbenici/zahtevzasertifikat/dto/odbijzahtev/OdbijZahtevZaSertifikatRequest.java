package rs.vakcinacija.sluzbenici.zahtevzasertifikat.dto.odbijzahtev;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

@Data
public class OdbijZahtevZaSertifikatRequest {

    @XmlElement(name = "razlog", required = true)
    private String razlog;
}
