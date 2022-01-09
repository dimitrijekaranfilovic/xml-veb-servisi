package rs.vakcinacija.sluzbenici.digitalnisertifikat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.model.DigitalniSertifikat;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.repository.DigitalniSertifikatExistRepository;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.repository.DigitalniSertifikatFusekiRepository;

import java.util.UUID;

@Service
public class DigitalniSertifikatService {
    private final DigitalniSertifikatExistRepository digitalniSertifikatExistRepository;
    private final DigitalniSertifikatFusekiRepository digitalniSertifikatFusekiRepository;

    @Autowired
    public DigitalniSertifikatService(DigitalniSertifikatExistRepository digitalniSertifikatExistRepository, DigitalniSertifikatFusekiRepository digitalniSertifikatFusekiRepository) {
        this.digitalniSertifikatExistRepository = digitalniSertifikatExistRepository;
        this.digitalniSertifikatFusekiRepository = digitalniSertifikatFusekiRepository;
    }

    public DigitalniSertifikat create(DigitalniSertifikat digitalniSertifikat) throws Exception {
        var id = digitalniSertifikatExistRepository.save(digitalniSertifikat);
        digitalniSertifikatFusekiRepository.save(id, digitalniSertifikat);
        return digitalniSertifikat;
    }

    public DigitalniSertifikat read(UUID id) throws Exception {
        return digitalniSertifikatExistRepository.read(id)
                .orElseThrow(() -> new RuntimeException("Cannot find digitalni sertifikat with id: " + id));
    }
}
