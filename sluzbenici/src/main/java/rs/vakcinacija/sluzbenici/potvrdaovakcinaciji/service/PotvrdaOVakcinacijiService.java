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
        addMetadata(potvrdaOVakcinaciji);
        this.fusekiRepository.save(id, potvrdaOVakcinaciji);
        return potvrdaOVakcinaciji;
    }

    @Override
    public PotvrdaOVakcinaciji read(UUID id) throws Exception {
        return this.existRepository.read(id).orElseThrow(() -> new RuntimeException("Cannot find digitalni sertifikat with id: " + id));
    }

    private void addMetadata(PotvrdaOVakcinaciji potvrdaOVakcinaciji){
        var sifra = potvrdaOVakcinaciji.getSifraPotvrde();
        var datumIzdavanja = potvrdaOVakcinaciji.getDatumIzdavanja();

        var licneInformacije = potvrdaOVakcinaciji.getLicneInformacije();
        var ime = licneInformacije.getIme();
        var prezime = licneInformacije.getPrezime();
        var jmbg = licneInformacije.getJmbg();
        var pol = licneInformacije.getPol();
        var vakcinacija = potvrdaOVakcinaciji.getVakcinacija();

        var ustanova = vakcinacija.getUstanova();

        sifra.rdf().property(PROP_SIFRA_DOKUMENTA).datatype(T_STRING);
        datumIzdavanja.rdf().property(PROP_DATUM_IZDAVANJA).datatype(T_DATE);
        ustanova.rdf().property("pred:ustanova").datatype(T_STRING);

        var pacijentUrl = RDF_PACIJENT_BASE + licneInformacije.getJmbg().getValue();
        ime.rdf().property(PROP_IME).datatype(T_STRING);
        prezime.rdf().property(PROP_PREZIME).datatype(T_STRING);
        jmbg.rdf().property(PROP_JMBG).datatype(T_STRING);
        pol.rdf().property(PROP_POL).datatype(T_STRING);

        licneInformacije.rdf().vocab(VOCAB).about(pacijentUrl);

        var vakcinaUrl = RDF_VAKCINA_BASE +  vakcinacija.getNazivVakcine().getValue();

        vakcinacija.rdf()
                .vocab(VOCAB)
                .about(vakcinaUrl)
                .rel("pred:upotrebljeno_na")
                .href(pacijentUrl)
                .typeof("pred:Vakcinacija");




    }
}
