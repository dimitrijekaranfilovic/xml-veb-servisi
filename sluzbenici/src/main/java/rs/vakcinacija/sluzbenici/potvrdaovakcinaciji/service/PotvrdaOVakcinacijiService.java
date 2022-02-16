package rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.model.DigitalniSertifikat;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model.PotvrdaOVakcinaciji;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.repository.PotvrdaOVakcinacijiExistRepository;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.repository.PotvrdaOVakcinacijiFusekiRepository;
import rs.vakcinacija.zajednicko.exception.DocumentNotFoundException;
import rs.vakcinacija.zajednicko.service.DocumentService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class PotvrdaOVakcinacijiService extends DocumentService<PotvrdaOVakcinaciji> {

    @Autowired
    public PotvrdaOVakcinacijiService(PotvrdaOVakcinacijiExistRepository potvrdaOVakcinacijiExistRepository,
                                      PotvrdaOVakcinacijiFusekiRepository potvrdaOVakcinacijiFusekiRepository){
        super(potvrdaOVakcinacijiExistRepository, potvrdaOVakcinacijiFusekiRepository);
    }

    public Optional<PotvrdaOVakcinaciji> readByCitizenEmail(String email) throws Exception {
        return existRepository.read(p -> p.provideEmail().equals(email)).stream().findFirst();
    }

    public void onIssueCertificate(PotvrdaOVakcinaciji potvrdaOVakcinaciji, DigitalniSertifikat sertifikat) throws Exception {
        potvrdaOVakcinaciji.ref("pred:kreirao")
                           .entity(sertifikat)
                           .type(DigitalniSertifikat.class)
                           .configure();
        super.update(potvrdaOVakcinaciji);
        log.info(String.format("Potvrda o vakcinaciji: %s izmenjena tako da ima vezu ka sertifikatu: %s", potvrdaOVakcinaciji.getId(), sertifikat.getId()));
    }

    protected void insertRDFMetadata(PotvrdaOVakcinaciji potvrdaOVakcinaciji) {
        var qrKod = potvrdaOVakcinaciji.getQrKod();
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

        qrKod.rdf().property(PROP_QR_KOD).datatype(T_STRING);
        potvrdaOVakcinaciji.getEmail().rdf().property(PROP_EMAIL).datatype(T_STRING);

        vakcinacija.rdf()
                .vocab(VOCAB)
                .about(vakcinaUrl)
                .rel("pred:upotrebljeno_na")
                .href(pacijentUrl)
                .typeof("pred:Vakcinacija");
    }
}
