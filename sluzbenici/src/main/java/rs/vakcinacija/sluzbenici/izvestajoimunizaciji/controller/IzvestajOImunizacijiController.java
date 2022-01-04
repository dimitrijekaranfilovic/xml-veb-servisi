package rs.vakcinacija.sluzbenici.izvestajoimunizaciji.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.sluzbenici.izvestajoimunizaciji.model.IzvestajOImunizaciji;

@RestController
@RequestMapping(value = "izvestaj-o-imunizaciji",
        produces = MediaType.APPLICATION_XML_VALUE,
        consumes = MediaType.APPLICATION_XML_VALUE)
public class IzvestajOImunizacijiController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IzvestajOImunizaciji test(@RequestBody IzvestajOImunizaciji izvestajOImunizaciji) {
        return izvestajOImunizaciji;
    }
}
