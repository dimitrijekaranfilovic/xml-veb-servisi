package rs.vakcinacija.imunizacija.interesovanje.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.vakcinacija.imunizacija.interesovanje.model.Interesovanje;

@RestController
@RequestMapping(value = "interesovanje",
        produces = MediaType.APPLICATION_XML_VALUE,
        consumes = MediaType.APPLICATION_XML_VALUE)
public class InteresovanjeController {

    @PostMapping
    public ResponseEntity<Interesovanje> get(@RequestBody Interesovanje interesovanje) {
        return new ResponseEntity<>(interesovanje, HttpStatus.CREATED);
    }

}
