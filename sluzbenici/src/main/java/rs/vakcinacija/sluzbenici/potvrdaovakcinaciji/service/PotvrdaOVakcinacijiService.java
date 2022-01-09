package rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model.PotvrdaOVakcinaciji;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.repository.PotvrdaOVakcinacijiExistRepository;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.repository.PotvrdaOVakcinacijiFusekiRepository;
import rs.vakcinacija.zajednicko.service.DocumentService;

import java.util.UUID;

@Service
public class PotvrdaOVakcinacijiService extends DocumentService<PotvrdaOVakcinaciji> {

    @Autowired
    public PotvrdaOVakcinacijiService(PotvrdaOVakcinacijiExistRepository potvrdaOVakcinacijiExistRepository,
                                      PotvrdaOVakcinacijiFusekiRepository potvrdaOVakcinacijiFusekiRepository){
        super(potvrdaOVakcinacijiExistRepository, potvrdaOVakcinacijiFusekiRepository);
    }


    @Override
    public PotvrdaOVakcinaciji create(PotvrdaOVakcinaciji potvrdaOVakcinaciji) throws Exception {
        var id = this.existRepository.save(potvrdaOVakcinaciji);
        this.fusekiRepository.save(id, potvrdaOVakcinaciji);
        return potvrdaOVakcinaciji;
    }

    @Override
    public PotvrdaOVakcinaciji read(UUID id) throws Exception {
        return this.existRepository.read(id).orElseThrow(() -> new RuntimeException("Cannot find digitalni sertifikat with id: " + id));
    }
}
