package rs.vakcinacija.sluzbenici.digitalnisertifikat.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.model.DigitalniSertifikat;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.repository.DigitalniSertifikatExistRepository;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.repository.DigitalniSertifikatFusekiRepository;
import rs.vakcinacija.zajednicko.service.DocumentService;

@Service
@Slf4j
public class DigitalniSertifikatService extends DocumentService<DigitalniSertifikat> {

    @Autowired
    public DigitalniSertifikatService(DigitalniSertifikatExistRepository digitalniSertifikatExistRepository, DigitalniSertifikatFusekiRepository digitalniSertifikatFusekiRepository) {
        super(digitalniSertifikatExistRepository, digitalniSertifikatFusekiRepository);
    }

    protected void insertRDFMetadata(DigitalniSertifikat digitalniSertifikat) {
        var brojSertifikata = digitalniSertifikat.getBrojSertifikata();
        brojSertifikata.rdf().property(PROP_SIFRA_DOKUMENTA).datatype(T_STRING);
        var datumIzdavanja = digitalniSertifikat.getDatumVremeIzdavanja();
        datumIzdavanja.rdf().property(PROP_DATUM_IZDAVANJA).datatype(T_DATE);
        var licneInformacije = digitalniSertifikat.getLicneInformacije();
        String podnosilacUrl;
        if (licneInformacije.getJmbg() != null) {
            var jmbg = licneInformacije.getJmbg();
            podnosilacUrl = RDF_PACIJENT_BASE + jmbg.getValue();
            jmbg.rdf().property(PROP_JMBG).datatype(T_STRING);
        } else {
            var brojPasosa = licneInformacije.getBrojPasosa();
            podnosilacUrl = RDF_PACIJENT_BASE + brojPasosa.getValue();
            brojPasosa.rdf().property(PROP_BROJ_PASOSA).datatype(T_STRING);
        }
        licneInformacije.rdf().vocab(VOCAB).about(podnosilacUrl);
        licneInformacije.getIme().rdf().property(PROP_IME).datatype(T_STRING);
        licneInformacije.getPrezime().rdf().property(PROP_PREZIME).datatype(T_STRING);
        var informacijeOSertifikatu = digitalniSertifikat.getInformacijeOSertifikatu();
        informacijeOSertifikatu.getQrKod().rdf().property(PROP_QR_KOD).datatype(T_STRING);
    }
}
