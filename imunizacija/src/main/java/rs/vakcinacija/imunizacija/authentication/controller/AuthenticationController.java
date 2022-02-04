package rs.vakcinacija.imunizacija.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.imunizacija.authentication.model.Gradjanin;
import rs.vakcinacija.imunizacija.authentication.service.AuthenticationService;

import java.util.UUID;

@Controller
@RequestMapping(value = "auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<Gradjanin> createNew(@RequestBody Gradjanin gradjanin) throws Exception {
        var savedGradjanin = authenticationService.createNew(gradjanin);
        return new ResponseEntity<Gradjanin>(savedGradjanin, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gradjanin> getById(@PathVariable UUID id) throws Exception {
        var savedGradjanin = authenticationService.read(id);
        return new ResponseEntity<Gradjanin>(savedGradjanin, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> testQuery(String email, String password) throws Exception {
        var ok = authenticationService.checkAuthData(email, password);
        return new ResponseEntity<Boolean>(ok, HttpStatus.OK);
    }
}
