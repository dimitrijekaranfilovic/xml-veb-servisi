package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model.IzvestajOImunizaciji;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.service.IzvestajOImunizacijiService;

import java.util.UUID;

@RestController
@RequestMapping(value = "izvestaj-o-imunizaciji")
public class IzvestajOImunizacijiController {


    private final IzvestajOImunizacijiService izvestajOImunizacijiService;

    @Autowired
    public IzvestajOImunizacijiController(IzvestajOImunizacijiService izvestajOImunizacijiService) {
        this.izvestajOImunizacijiService = izvestajOImunizacijiService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IzvestajOImunizaciji create(@RequestBody IzvestajOImunizaciji izvestajOImunizaciji) throws Exception {
        var id = this.izvestajOImunizacijiService.create(izvestajOImunizaciji);
        System.out.println("Created izvestaj o imunizaciji with id: " + id);
        return izvestajOImunizaciji;
    }

    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public IzvestajOImunizaciji read(@PathVariable UUID id) throws Exception {
        return this.izvestajOImunizacijiService.read(id);
    }
}
