package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model.IzvestajOImunizaciji;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.repository.IzvestajOImunizacijiExistRepository;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.repository.IzvestajOImunizacijiFusekiRepository;
import rs.vakcinacija.zajednicko.exception.DocumentNotFoundException;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.service.DocumentService;

import java.util.Date;
import java.util.UUID;

@Service
public class IzvestajOImunizacijiService extends DocumentService<IzvestajOImunizaciji> {

    @Autowired
    public IzvestajOImunizacijiService(IzvestajOImunizacijiExistRepository izvestajOImunizacijiExistRepository,
                                IzvestajOImunizacijiFusekiRepository izvestajOImunizacijiFusekiRepository){
        super(izvestajOImunizacijiExistRepository, izvestajOImunizacijiFusekiRepository);
    }

    public IzvestajOImunizaciji generateIzvestaj(Date periodOd, Date periodDo) throws Exception {
        //TODO: ovdje pozovi imunizacija servis
        var izvestajOImunizaciji = new IzvestajOImunizaciji(RDFDate.of(periodOd), RDFDate.of(periodDo), RDFDate.of(new Date()));
        this.create(izvestajOImunizaciji);
        return izvestajOImunizaciji;
    }

    protected void insertRDFMetadata(IzvestajOImunizaciji izvestajOImunizaciji) {
        var periodOd = izvestajOImunizaciji.getPeriodOd();
        var periodDo = izvestajOImunizaciji.getPeriodDo();
        var datumIzdavanja = izvestajOImunizaciji.getDatumIzdavanja();

        periodOd.rdf().property("pred:period_od").datatype(T_DATE);
        periodDo.rdf().property("pred:period_do").datatype(T_DATE);
        datumIzdavanja.rdf().property(PROP_DATUM_IZDAVANJA).datatype(T_DATE);

    }
}
