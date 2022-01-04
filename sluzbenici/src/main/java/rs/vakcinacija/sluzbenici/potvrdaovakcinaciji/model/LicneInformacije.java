package rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model;

import rs.vakcinacija.sluzbenici.zajednicko.model.LicniPodaci;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "licne_informacije")
@XmlType(name = "TLicne_informacije")
public class LicneInformacije extends LicniPodaci {

    public LicneInformacije() {
    }

    public LicneInformacije(String ime, String prezime, String jmbg, String pol) {
        super(ime, prezime, jmbg, pol);
    }
}
