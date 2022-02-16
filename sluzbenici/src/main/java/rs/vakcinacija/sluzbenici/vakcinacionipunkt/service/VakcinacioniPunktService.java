package rs.vakcinacija.sluzbenici.vakcinacionipunkt.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.exception.VaccineDoesntExistException;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.exception.VaccineExistsException;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.model.DostupnaVakcina;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.model.VakcinacioniPunkt;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.repository.VakcinacioniPunktExistRepository;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.repository.VakcinacioniPunktFusekiRepository;
import rs.vakcinacija.zajednicko.email.model.SendEmailRequest;
import rs.vakcinacija.zajednicko.email.service.EmailService;
import rs.vakcinacija.zajednicko.service.DocumentService;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class VakcinacioniPunktService extends DocumentService<VakcinacioniPunkt> {

    private final EmailService emailService;

    @Autowired
    public VakcinacioniPunktService(VakcinacioniPunktExistRepository vakcinacioniPunktExistRepository, VakcinacioniPunktFusekiRepository vakcinacioniPunktFusekiRepository, EmailService emailService) {
        super(vakcinacioniPunktExistRepository, vakcinacioniPunktFusekiRepository);
        this.emailService = emailService;
    }


//    public String create(VakcinacioniPunkt vakcinacioniPunkt) throws Exception {
//        //TODO: dodati provjeru da li punkt sa datim imenom vec postoji
//        //ovo ispod ne radi
////        ResourceSet results = this.vakcinacioniPunktExistRepository.runXPathQuery("//naziv_punkta[text()='" + vakcinacioniPunkt.getNazivPunkta() + "']");
////        var iterator = results.getIterator();
////        if(iterator.hasMoreResources()){
////            throw new PunktExistsException(String.format("Punkt %s vec postoji.", vakcinacioniPunkt.getNazivPunkta()));
////        }
//        var id = this.vakcinacioniPunktExistRepository.save(vakcinacioniPunkt);
//        return id.toString();
//    }


//    public VakcinacioniPunkt read(UUID id) throws Exception {
//        return this.vakcinacioniPunktExistRepository.read(id)
//                .orElseThrow(() -> new DocumentNotFoundException(String.format("Cannot find document with id: '%s'.", id)));
//    }
//
//    public KolekcijaVakcinacionihPunktova read() throws Exception {
//        return KolekcijaVakcinacionihPunktova.of(this.vakcinacioniPunktExistRepository.read());
//    }

    public VakcinacioniPunkt createTermin(UUID id, Date termin) throws Exception {
        var punkt = this.read(id);
        punkt.addTermin(termin);
        this.existRepository.save(punkt);
        return punkt;
    }

    public VakcinacioniPunkt createDostupnaVakcina(UUID id, String nazivVakcine, Integer stanjeVakcine) throws Exception {
        var punkt = this.read(id);
        if (punkt.getDostupneVakcine().getDostupneVakcine().stream().anyMatch(vakcina -> vakcina.getNazivVakcine().equals(nazivVakcine)))
            throw new VaccineExistsException(String.format("Vakcina %s je vec definisana za punkt %s.", nazivVakcine, punkt.getNazivPunkta()));
        punkt.addDostupnaVakcina(new DostupnaVakcina(nazivVakcine, stanjeVakcine));
        this.existRepository.save(punkt);
        return punkt;
    }

    public VakcinacioniPunkt updateDostupnaVakcina(UUID id, String nazivVakcine, Integer novoStanje) throws Exception {
        var punkt = this.read(id);

        var dostupnaVakcinaOptional = punkt.getDostupneVakcine().getDostupneVakcine()
                .stream()
                .filter(v -> v.getNazivVakcine().equals(nazivVakcine))
                .findFirst();
        if (dostupnaVakcinaOptional.isEmpty())
            throw new VaccineDoesntExistException(String.format("Vakcina %s nije definisana za punkt %s.", nazivVakcine, punkt.getNazivPunkta()));

        var dostupnaVakcina = dostupnaVakcinaOptional.get();
        dostupnaVakcina.setNazivVakcine(nazivVakcine);
        dostupnaVakcina.setStanjeVakcine(novoStanje);
        this.existRepository.save(punkt);

        return punkt;
    }

    public void assignAppointment(String mesto, Collection<String> odabraneVakcine, String email,
                                  UUID interesovanjeId) throws Exception {
        var punkt = this.existRepository.read((p) -> p.getNazivPunkta().equals(mesto))
                .stream().findFirst().orElseThrow();
        var dostupneVakcine = punkt.getDostupneVakcine().getDostupneVakcine();

        var hasVaccines = false;
        for (var odabranaVakcina :
                odabraneVakcine) {
            for (var dostupnaVakcina :
                    dostupneVakcine) {
                if (dostupnaVakcina.getNazivVakcine().equals(odabranaVakcina)
                        && dostupnaVakcina.getStanjeVakcine() > 0) {
                    hasVaccines = true;
                    break;
                }
            }
        }

        if (hasVaccines) {
            var termini = punkt.getTermini().getTermini();
            if (!termini.isEmpty()) {
                var dodeljeniTermin = termini.stream().findFirst();
                dodeljeniTermin.ifPresent(termini::remove);
                dodeljeniTermin.ifPresent(p -> sendInteresovanjeRecievedMessage(email, p, mesto));
            }
        }
    }


    @Override
    protected void insertRDFMetadata(VakcinacioniPunkt vakcinacioniPunkt) {

    }

    @Async
    public void sendInteresovanjeRecievedMessage(String email, Date datum, String mesto) {
//        var to = email;
        var to = "njmarko1991@gmail.com";
        var subject = "Обавештење о додељеном термину за вакцинацију";
        var sb = new StringBuilder();
        sb.append("Поштовани, \n");
        sb.append("\nНа основу вашег исказаног интересовања додељен вам је термин за вакцинацију:\n");
        sb.append("\n\tМесто: ").append(mesto).append("\n");
        sb.append("\n\tВреме: ").append(datum).append("\n");

        sb.append("\n\n\nСрдачан поздрав,\n");
        sb.append("Информациони систем за вакцинацију грађана\n");

        emailService.sendEmail(new SendEmailRequest(to, subject, sb.toString()));
    }
}
