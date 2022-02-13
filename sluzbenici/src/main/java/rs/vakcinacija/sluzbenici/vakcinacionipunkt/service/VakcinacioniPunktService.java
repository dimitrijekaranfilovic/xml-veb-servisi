package rs.vakcinacija.sluzbenici.vakcinacionipunkt.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xmldb.api.base.ResourceSet;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.exception.PunktExistsException;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.exception.VaccineDoesntExistException;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.exception.VaccineExistsException;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.model.DostupnaVakcina;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.model.VakcinacioniPunkt;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.repository.VakcinacioniPunktExistRepository;
import rs.vakcinacija.zajednicko.exception.DocumentNotFoundException;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class VakcinacioniPunktService {

    private final VakcinacioniPunktExistRepository vakcinacioniPunktExistRepository;

    @Autowired
    public VakcinacioniPunktService(VakcinacioniPunktExistRepository vakcinacioniPunktExistRepository) {
        this.vakcinacioniPunktExistRepository = vakcinacioniPunktExistRepository;
    }


    public String create(VakcinacioniPunkt vakcinacioniPunkt) throws Exception {
        //TODO: dodati provjeru da li punkt sa datim imenom vec postoji
        //ovo ispod ne radi
//        ResourceSet results = this.vakcinacioniPunktExistRepository.runXPathQuery("//naziv_punkta[text()='" + vakcinacioniPunkt.getNazivPunkta() + "']");
//        var iterator = results.getIterator();
//        if(iterator.hasMoreResources()){
//            throw new PunktExistsException(String.format("Punkt %s vec postoji.", vakcinacioniPunkt.getNazivPunkta()));
//        }
        var id = this.vakcinacioniPunktExistRepository.save(vakcinacioniPunkt);
        return id.toString();
    }


    public VakcinacioniPunkt read(UUID id) throws Exception {
        return this.vakcinacioniPunktExistRepository.read(id)
                .orElseThrow(() -> new DocumentNotFoundException(String.format("Cannot find document with id: '%s'.", id)));
    }

    public VakcinacioniPunkt createTermin(UUID id, Date termin) throws Exception {
        var punkt = this.read(id);
        punkt.addTermin(termin);
        this.vakcinacioniPunktExistRepository.save(punkt);
        return punkt;
    }

    public VakcinacioniPunkt createDostupnaVakcina(UUID id, String nazivVakcine, Integer stanjeVakcine) throws Exception {
        var punkt = this.read(id);
        if (punkt.getDostupneVakcine().getDostupneVakcine().stream().anyMatch(vakcina -> vakcina.getNazivVakcine().equals(nazivVakcine)))
            throw new VaccineExistsException(String.format("Vakcina %s je vec definisana za punkt %s.", nazivVakcine, punkt.getNazivPunkta()));
        punkt.addDostupnaVakcina(new DostupnaVakcina(nazivVakcine, stanjeVakcine));
        this.vakcinacioniPunktExistRepository.save(punkt);
        return punkt;
    }

    public VakcinacioniPunkt updateDostupnaVakcina(UUID id, String nazivVakcine, Integer novoStanje) throws Exception {
        var punkt  = this.read(id);

        var dostupnaVakcinaOptional = punkt.getDostupneVakcine().getDostupneVakcine()
                .stream()
                .filter(v -> v.getNazivVakcine().equals(nazivVakcine))
                .findFirst();
        if (dostupnaVakcinaOptional.isEmpty())
            throw new VaccineDoesntExistException(String.format("Vakcina %s nije definisana za punkt %s.", nazivVakcine, punkt.getNazivPunkta()));

        var dostupnaVakcina = dostupnaVakcinaOptional.get();
        dostupnaVakcina.setNazivVakcine(nazivVakcine);
        dostupnaVakcina.setStanjeVakcine(novoStanje);
        this.vakcinacioniPunktExistRepository.save(punkt);

        return punkt;
    }


}
