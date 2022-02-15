package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.sluzbenici.digitalnisertifikat.repository.DigitalniSertifikatExistRepository;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model.DataDoza;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model.IzvestajOImunizaciji;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model.Stavka;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.repository.IzvestajOImunizacijiExistRepository;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.repository.IzvestajOImunizacijiFusekiRepository;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.model.PotvrdaOVakcinaciji;
import rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.repository.PotvrdaOVakcinacijiExistRepository;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.service.ZahtevZaSertifikatClient;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.model.RDFInteger;
import rs.vakcinacija.zajednicko.model.RDFString;
import rs.vakcinacija.zajednicko.service.DocumentService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class IzvestajOImunizacijiService extends DocumentService<IzvestajOImunizaciji> {

    private final ZahtevZaSertifikatClient zahtevZaSertifikatClient;
    private final DigitalniSertifikatExistRepository digitalniSertifikatExistRepository;
    private final PotvrdaOVakcinacijiExistRepository potvrdaOVakcinacijiExistRepository;

    @Autowired
    public IzvestajOImunizacijiService(IzvestajOImunizacijiExistRepository izvestajOImunizacijiExistRepository,
                                       IzvestajOImunizacijiFusekiRepository izvestajOImunizacijiFusekiRepository,
                                       ZahtevZaSertifikatClient zahtevZaSertifikatClient,
                                       DigitalniSertifikatExistRepository digitalniSertifikatExistRepository, PotvrdaOVakcinacijiExistRepository potvrdaOVakcinacijiExistRepository) {
        super(izvestajOImunizacijiExistRepository, izvestajOImunizacijiFusekiRepository);
        this.zahtevZaSertifikatClient = zahtevZaSertifikatClient;
        this.digitalniSertifikatExistRepository = digitalniSertifikatExistRepository;
        this.potvrdaOVakcinacijiExistRepository = potvrdaOVakcinacijiExistRepository;
    }

    public IzvestajOImunizaciji generateIzvestaj(Date periodOd, Date periodDo) throws Exception {
        var izvestajOImunizaciji = new IzvestajOImunizaciji(RDFDate.of(periodOd), RDFDate.of(periodDo), RDFDate.of(new Date()));

        int primljenoZahteva = countPrimljeneZahtevZaSertifikat(periodOd, periodDo);
        izvestajOImunizaciji.setPrimljenoZahtevaZaSertifikat(RDFInteger.of(primljenoZahteva));

        int izdatoZahteva = countIzdatoZahteva(periodOd, periodDo);
        izvestajOImunizaciji.setIzdatoZahtevaZaSertifikat(RDFInteger.of(izdatoZahteva));

        var kolekcijaPotvrdaOVakcinaciji = this.potvrdaOVakcinacijiExistRepository.read()
                .stream()
                .filter(potvrdaOVakcinaciji -> dateBetween(periodOd, periodDo, potvrdaOVakcinaciji.getDatumIzdavanja().getValue()))
                .collect(Collectors.toList());

        //mozda ovo dvoje u ovu funkciju da se izvuku?
        var poDozama = countPoDozama(kolekcijaPotvrdaOVakcinaciji);
        var poProizvodjacima = countPoProizvodjacima(kolekcijaPotvrdaOVakcinaciji);

        poDozama.forEach((key, value) ->
                izvestajOImunizaciji
                        .getDateDozeVakcina()
                        .getDateDoze()
                        .add(new DataDoza(RDFInteger.of(key), RDFInteger.of(value))));

        poProizvodjacima.forEach((key, value) -> izvestajOImunizaciji
                .getRaspodelaPoProizvodjacima()
                .getStavke()
                .add(new Stavka(RDFString.of(key), RDFInteger.of(value))));

        this.create(izvestajOImunizaciji);
        return izvestajOImunizaciji;
    }

    private Integer countPrimljeneZahtevZaSertifikat(Date periodOd, Date periodDo) {
        var kolekcijaZahtevaZaSertifikat = this.zahtevZaSertifikatClient.readTotal();
        var lista = kolekcijaZahtevaZaSertifikat.getZahteviZaSertifikat();
        if (lista == null)
            return 0;
        long res = lista
                .stream()
                .filter(zahtev -> dateBetween(periodOd, periodDo, zahtev.getDatum().getValue()))
                .count();
        return (int) res;
    }

    private Integer countIzdatoZahteva(Date periodOd, Date periodDo) throws Exception {
        var kolekcijaDigitalnihSertifikata = this.digitalniSertifikatExistRepository.read();
        long res = kolekcijaDigitalnihSertifikata
                .stream()
                .filter(digitalniSertifikat -> dateBetween(periodOd, periodDo, digitalniSertifikat.getDatumVremeIzdavanja().getValue()))
                .count();
        return (int) res;
    }

    private Map<String, Integer> countPoProizvodjacima(List<PotvrdaOVakcinaciji> kolekcijaPotvrdaOVakcinaciji) {
        Map<String, Integer> map = new HashMap<>();
        kolekcijaPotvrdaOVakcinaciji.forEach(potvrdaOVakcinaciji -> {
            var proizvodjac = potvrdaOVakcinaciji.getVakcinacija().getNazivVakcine().getValue();
            var brojDoza = potvrdaOVakcinaciji.getVakcinacija().getDoze().getDoze().size();
            if (!map.containsKey(proizvodjac))
                map.put(proizvodjac, brojDoza);
            else {
                var prev = map.get(proizvodjac);
                map.put(proizvodjac, prev + brojDoza);
            }
        });
        return map;
    }

    private Map<Integer, Integer> countPoDozama(List<PotvrdaOVakcinaciji> kolekcijaPotvrdaOVakcinaciji) {
        Map<Integer, Integer> map = new HashMap<>();
        kolekcijaPotvrdaOVakcinaciji.forEach(potvrdaOVakcinaciji -> {
            var doze = potvrdaOVakcinaciji.getVakcinacija().getDoze().getDoze();
            doze.forEach(doza -> {
                var brojDoze = doza.getBrojDoze().getValue();
                if (!map.containsKey(brojDoze))
                    map.put(brojDoze, 1);
                else {
                    var prev = map.get(brojDoze);
                    map.put(brojDoze, prev + 1);
                }
            });
        });
        return map;
    }


    private boolean dateBetween(Date from, Date to, Date toCheck) {
        return toCheck.after(from) && toCheck.before(to);
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
