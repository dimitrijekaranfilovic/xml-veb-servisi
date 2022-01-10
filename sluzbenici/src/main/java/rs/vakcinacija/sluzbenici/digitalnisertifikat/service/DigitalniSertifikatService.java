package rs.vakcinacija.sluzbenici.digitalnisertifikat.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.exception.DigitalniSertifikatNotFoundException;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.model.DigitalniSertifikat;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.repository.DigitalniSertifikatExistRepository;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.repository.DigitalniSertifikatFusekiRepository;
import rs.vakcinacija.zajednicko.service.DocumentService;

import java.util.UUID;

@Service
@Slf4j
public class DigitalniSertifikatService extends DocumentService<DigitalniSertifikat> {
    @Autowired
    public DigitalniSertifikatService(DigitalniSertifikatExistRepository digitalniSertifikatExistRepository, DigitalniSertifikatFusekiRepository digitalniSertifikatFusekiRepository) {
        super(digitalniSertifikatExistRepository, digitalniSertifikatFusekiRepository);
    }

    public DigitalniSertifikat create(DigitalniSertifikat digitalniSertifikat) throws Exception {
        var id = existRepository.save(digitalniSertifikat);
        populateMetadata(digitalniSertifikat);
        fusekiRepository.save(id, digitalniSertifikat);
        return digitalniSertifikat;
    }

    public DigitalniSertifikat read(UUID id) throws Exception {
        return existRepository.read(id)
                .orElseThrow(() -> new DigitalniSertifikatNotFoundException("Cannot find digitalni sertifikat with id: " + id));
    }

    private void populateMetadata(DigitalniSertifikat digitalniSertifikat) {
        var brojSertifikata = digitalniSertifikat.getBrojSertifikata();
        brojSertifikata.setProperty(PROP_SIFRA_DOKUMENTA);
        brojSertifikata.setDatatype(T_STRING);
        var datumIzdavanja = digitalniSertifikat.getDatumVremeIzdavanja();
        datumIzdavanja.setProperty(PROP_DATUM_IZDAVANJA);
        datumIzdavanja.setDatatype(T_DATE);
        var licneInformacije = digitalniSertifikat.getLicneInformacije();
        String podnosilacUrl;
        if (licneInformacije.getJmbg() != null) {
            var jmbg = licneInformacije.getJmbg();
            podnosilacUrl = RDF_PACIJENT_BASE + jmbg.getValue();
            jmbg.setProperty(PROP_JMBG);
            jmbg.setDatatype(T_STRING);
        } else {
            var brojPasosa = licneInformacije.getBrojPasosa();
            podnosilacUrl = RDF_PACIJENT_BASE + brojPasosa.getValue();
            brojPasosa.setProperty(PROP_BROJ_PASOSA);
            brojPasosa.setDatatype(T_STRING);
        }
        licneInformacije.setVocab(VOCAB);
        licneInformacije.setAbout(podnosilacUrl);
        licneInformacije.getIme().setProperty(PROP_IME);
        licneInformacije.getIme().setDatatype(T_STRING);
        licneInformacije.getPrezime().setProperty(PROP_PREZIME);
        licneInformacije.getPrezime().setDatatype(T_STRING);
        var informacijeOSertifikatu = digitalniSertifikat.getInformacijeOSertifikatu();
        informacijeOSertifikatu.getQrKod().setProperty(PROP_QR_KOD);
        informacijeOSertifikatu.getQrKod().setDatatype(T_STRING);
    }
}
