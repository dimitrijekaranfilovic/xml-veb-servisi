package rs.vakcinacija.sluzbenici.vakcinacionipunkt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.model.VakcinacioniPunkt;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.repository.VakcinacioniPunktExistRepository;
import rs.vakcinacija.zajednicko.exception.DocumentNotFoundException;

import java.util.UUID;

@Component
public class VakcinacioniPunktService {

    private final VakcinacioniPunktExistRepository vakcinacioniPunktExistRepository;

    @Autowired
    public VakcinacioniPunktService(VakcinacioniPunktExistRepository vakcinacioniPunktExistRepository) {
        this.vakcinacioniPunktExistRepository = vakcinacioniPunktExistRepository;
    }


    public String create(VakcinacioniPunkt vakcinacioniPunkt) throws Exception {
        var id = this.vakcinacioniPunktExistRepository.save(vakcinacioniPunkt);
        return id.toString();
    }


    public VakcinacioniPunkt read(UUID id) throws Exception {
        return this.vakcinacioniPunktExistRepository.read(id)
                .orElseThrow(() -> new DocumentNotFoundException(String.format("Cannot find document with id: '%s'.", id)));
    }

}
