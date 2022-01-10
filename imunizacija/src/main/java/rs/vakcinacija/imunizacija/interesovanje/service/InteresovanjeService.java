package rs.vakcinacija.imunizacija.interesovanje.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.imunizacija.interesovanje.model.Interesovanje;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;
import rs.vakcinacija.zajednicko.metadata.repository.FusekiRepository;
import rs.vakcinacija.zajednicko.service.DocumentService;

import java.util.UUID;

@Service
public class InteresovanjeService extends DocumentService<Interesovanje> {
    @Autowired
    public InteresovanjeService(ExistRepository<Interesovanje> existRepository,
                                FusekiRepository<Interesovanje> fusekiRepository) {
        super(existRepository, fusekiRepository);
    }

    @Override
    public Interesovanje create(Interesovanje interesovanje) throws Exception {
        var id = existRepository.save(interesovanje);
        fusekiRepository.save(id, interesovanje);
        return interesovanje;
    }

    @Override
    public Interesovanje read(UUID id) throws Exception {
        return existRepository.read(id)
                .orElseThrow(() -> new RuntimeException("Cannot find interesovanje with id: " + id));
    }
}
