package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model.IzvestajOImunizaciji;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.repository.IzvestajOImunizacijiExistRepository;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.repository.IzvestajOImunizacijiFusekiRepository;
import rs.vakcinacija.zajednicko.service.DocumentService;

import java.util.UUID;

@Service
public class IzvestajOImunizacijiService extends DocumentService<IzvestajOImunizaciji> {

    @Autowired
    public IzvestajOImunizacijiService(IzvestajOImunizacijiExistRepository izvestajOImunizacijiExistRepository,
                                IzvestajOImunizacijiFusekiRepository izvestajOImunizacijiFusekiRepository){
        super(izvestajOImunizacijiExistRepository, izvestajOImunizacijiFusekiRepository);
    }


    @Override
    public IzvestajOImunizaciji create(IzvestajOImunizaciji izvestajOImunizaciji) throws Exception {
        var id = this.existRepository.save(izvestajOImunizaciji);
        this.fusekiRepository.save(id, izvestajOImunizaciji);
        return izvestajOImunizaciji;
    }

    @Override
    public IzvestajOImunizaciji read(UUID id) throws Exception {
        return this.existRepository.read(id)
                .orElseThrow(() -> new RuntimeException("Cannot find digitalni sertifikat with id: " + id));
    }
}
