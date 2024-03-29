package rs.vakcinacija.sluzbenici.vakcinacionipunkt.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rs.vakcinacija.sluzbenici.config.email.EmailClient;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.exception.VaccineDoesntExistException;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.exception.VaccineExistsException;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.model.DostupnaVakcina;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.model.OdabraneVakcine;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.model.VakcinacioniPunkt;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.model.ZainteresovaniPacijent;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.repository.VakcinacioniPunktExistRepository;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.repository.VakcinacioniPunktFusekiRepository;
import rs.vakcinacija.sluzbenici.zahtevzasertifikat.service.ZahtevZaSertifikatService;
import rs.vakcinacija.zajednicko.email.model.SendEmailRequest;
import rs.vakcinacija.zajednicko.service.DocumentService;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

@Slf4j
@Component
public class VakcinacioniPunktService extends DocumentService<VakcinacioniPunkt> {

    private final EmailClient emailService;

    private final InteresovanjeClient client;

    private final ZahtevZaSertifikatService zahtevZaSertifikatService;

    @Autowired
    public VakcinacioniPunktService(VakcinacioniPunktExistRepository vakcinacioniPunktExistRepository, VakcinacioniPunktFusekiRepository vakcinacioniPunktFusekiRepository, EmailClient emailService, InteresovanjeClient client, ZahtevZaSertifikatService zahtevZaSertifikatService) {
        super(vakcinacioniPunktExistRepository, vakcinacioniPunktFusekiRepository);
        this.emailService = emailService;
        this.client = client;
        this.zahtevZaSertifikatService = zahtevZaSertifikatService;
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
        this.assignAppointmentsToQueuedInteresovanja(punkt);
        return punkt;
    }

    public VakcinacioniPunkt createDostupnaVakcina(UUID id, String nazivVakcine, Integer stanjeVakcine) throws Exception {
        var punkt = this.read(id);
        if (punkt.getDostupneVakcine().getDostupneVakcine().stream().anyMatch(vakcina -> vakcina.getNazivVakcine().equals(nazivVakcine)))
            throw new VaccineExistsException(String.format("Vakcina %s je vec definisana za punkt %s.", nazivVakcine, punkt.getNazivPunkta()));
        punkt.addDostupnaVakcina(new DostupnaVakcina(nazivVakcine, stanjeVakcine));
        this.existRepository.save(punkt);
        this.assignAppointmentsToQueuedInteresovanja(punkt);
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
        this.assignAppointmentsToQueuedInteresovanja(punkt);
        return punkt;
    }

    public void processArrivingInteresovanje(String mesto, Collection<String> odabraneVakcine, String email,
                                             UUID interesovanjeId) throws Exception {
        var punktOptional = this.existRepository.read(p -> p.getNazivPunkta().equals(mesto))
                .stream().findFirst();
        VakcinacioniPunkt punkt;
        if (punktOptional.isEmpty()) {
            var created = new VakcinacioniPunkt();
            created.setNazivPunkta(mesto);
            punkt = this.create(created);
        } else {
            punkt = punktOptional.get();
        }

        if (!assignAppointment(punkt, odabraneVakcine, email, interesovanjeId)) {
            punkt.getZainteresovaniPacijenti().getZainteresovaniPacijenti()
                    .add(new ZainteresovaniPacijent(new OdabraneVakcine(odabraneVakcine), email, interesovanjeId));
            this.existRepository.save(punkt);
        }
    }

    public void assignAppointmentsToQueuedInteresovanja(VakcinacioniPunkt punkt) throws Exception {
        var assigned = new HashSet<ZainteresovaniPacijent>();
        punkt.getZainteresovaniPacijenti().getZainteresovaniPacijenti()
                .forEach(p ->
                        {
                            try {
                                if (assignAppointment(punkt, p.getOdabraneVakcine().getOdabraneVakcine(),
                                        p.getEmail(), p.getInteresovanjeId())) {
                                    assigned.add(p);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                );
        punkt.getZainteresovaniPacijenti().getZainteresovaniPacijenti().removeAll(assigned);
        this.existRepository.save(punkt);
    }

    public boolean assignAppointment(VakcinacioniPunkt punkt, Collection<String> odabraneVakcine, String email,
                                     UUID interesovanjeId) throws Exception {
        var dostupneVakcine = punkt.getDostupneVakcine().getDostupneVakcine();
        var mesto = punkt.getNazivPunkta();

        for (var odabranaVakcina :
                odabraneVakcine) {
            for (var dostupnaVakcina :
                    dostupneVakcine) {
                if ((dostupnaVakcina.getNazivVakcine().equals(odabranaVakcina) || odabranaVakcina.equals("Било која"))
                        && dostupnaVakcina.getStanjeVakcine() > 0) {

                    var termini = punkt.getTermini().getTermini();
                    if (!termini.isEmpty()) {
                        var dodeljeniTermin = termini.stream().filter(p -> {
                            var today = new Date();
                            long diffInMillies = p.getTime() - today.getTime();
                            return diffInMillies > 0 && diffInMillies < 604800000;

                        }).findFirst();
                        if (dodeljeniTermin.isPresent()) {
                            dodeljeniTermin.ifPresent(termini::remove);
                            dostupnaVakcina.setStanjeVakcine(dostupnaVakcina.getStanjeVakcine() - 1);
                            dodeljeniTermin.ifPresent(p -> sendInteresovanjeRecievedMessage(email, p, mesto, dostupnaVakcina.getNazivVakcine()));
                            this.existRepository.save(punkt);
                            this.setZahtevTerminDate(interesovanjeId, dodeljeniTermin.orElseThrow());
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    protected void insertRDFMetadata(VakcinacioniPunkt vakcinacioniPunkt) {

    }

    @Async
    public void sendInteresovanjeRecievedMessage(String email, Date datum, String mesto, String izabranaVakcina) {
        var to = email;
        var subject = "Обавештење о додељеном термину за вакцинацију";
        var sb = new StringBuilder();
        sb.append("Поштовани, \n");
        sb.append("\nНа основу Вашег исказаног интересовања додељен Вам је термин за вакцинацију:\n");
        sb.append("\n\tМесто: ").append(mesto).append("\n");
        sb.append("\n\tВреме: ").append(datum).append("\n");
        sb.append("\n\tИзабрана вакцина: ").append(izabranaVakcina).append("\n");

        sb.append("\n\n\nСрдачан поздрав,\n");
        sb.append("Ваш портал за имунизацију\n");

        emailService.sendEmail(new SendEmailRequest(to, subject, sb.toString()));
    }

    public void setZahtevTerminDate(UUID id, Date datumTermina) throws Exception {
        this.client.setDatumTermina(id, datumTermina.getTime());
    }
}
