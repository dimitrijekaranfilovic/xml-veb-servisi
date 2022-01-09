package rs.vakcinacija.sluzbenici.digitalnisertifikat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.model.DigitalniSertifikat;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.repository.DigitalniSertifikatExistRepository;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.repository.DigitalniSertifikatFusekiRepository;
import rs.vakcinacija.zajednicko.service.DocumentService;

import java.util.UUID;

@Service
public class DigitalniSertifikatService extends DocumentService<DigitalniSertifikat> {
    @Autowired
    public DigitalniSertifikatService(DigitalniSertifikatExistRepository digitalniSertifikatExistRepository, DigitalniSertifikatFusekiRepository digitalniSertifikatFusekiRepository) {
        super(digitalniSertifikatExistRepository, digitalniSertifikatFusekiRepository);
    }

    public DigitalniSertifikat create(DigitalniSertifikat digitalniSertifikat) throws Exception {
        var id = existRepository.save(digitalniSertifikat);
        fusekiRepository.save(id, digitalniSertifikat);
        return digitalniSertifikat;
    }

    public DigitalniSertifikat read(UUID id) throws Exception {
        return existRepository.read(id)
                .orElseThrow(() -> new RuntimeException("Cannot find digitalni sertifikat with id: " + id));
    }
}
