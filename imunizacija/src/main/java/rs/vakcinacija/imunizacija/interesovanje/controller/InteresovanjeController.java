package rs.vakcinacija.imunizacija.interesovanje.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.imunizacija.interesovanje.model.Interesovanje;
import rs.vakcinacija.imunizacija.interesovanje.repository.InteresovanjeRepository;

import java.util.UUID;

@RestController
@RequestMapping(value = "interesovanje",
        produces = MediaType.APPLICATION_XML_VALUE,
        consumes = MediaType.APPLICATION_XML_VALUE)
public class InteresovanjeController {

    private final InteresovanjeRepository interesovanjeRepository;

    @Autowired
    public InteresovanjeController(InteresovanjeRepository interesovanjeRepository) {
        this.interesovanjeRepository = interesovanjeRepository;
    }

    @PostMapping
    public ResponseEntity<Interesovanje> interesovanjeWrite(@RequestBody Interesovanje interesovanje) throws Exception {
        var id = interesovanjeRepository.save(interesovanje);
        System.out.println("Created interesovanje with id : " + id);
        return new ResponseEntity<>(interesovanje, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Interesovanje> interesovanjeRead(@PathVariable UUID id) throws Exception {
        return new ResponseEntity<>(interesovanjeRepository.read(id).get(), HttpStatus.OK);
    }

}
