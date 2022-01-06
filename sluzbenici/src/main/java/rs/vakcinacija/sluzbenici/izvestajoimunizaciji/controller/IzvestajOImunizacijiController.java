package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.repository.IzvestajOImunizacijiRepository;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model.IzvestajOImunizaciji;

import java.util.UUID;

@RestController
@RequestMapping(value = "izvestaj-o-imunizaciji",
        produces = MediaType.APPLICATION_XML_VALUE,
        consumes = MediaType.APPLICATION_XML_VALUE)
public class IzvestajOImunizacijiController {


    private final IzvestajOImunizacijiRepository izvestajOImunizacijiRepository;

    @Autowired
    public IzvestajOImunizacijiController(IzvestajOImunizacijiRepository izvestajOImunizacijiRepository) {
        this.izvestajOImunizacijiRepository = izvestajOImunizacijiRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IzvestajOImunizaciji create(@RequestBody IzvestajOImunizaciji izvestajOImunizaciji) throws Exception {
        var id = izvestajOImunizacijiRepository.save(izvestajOImunizaciji);
        System.out.println("Created izvestaj o imunizaciji with id: " + id);
        return izvestajOImunizaciji;
    }

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public IzvestajOImunizaciji read(@PathVariable UUID id) throws Exception {
        return izvestajOImunizacijiRepository.read(id).get();
    }
}
