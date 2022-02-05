package rs.vakcinacija.imunizacija.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rs.vakcinacija.imunizacija.authentication.dto.AuthRequest;
import rs.vakcinacija.imunizacija.authentication.dto.AuthResponse;
import rs.vakcinacija.imunizacija.authentication.model.Gradjanin;
import rs.vakcinacija.imunizacija.authentication.service.AuthenticationService;
import rs.vakcinacija.imunizacija.config.jwt.JwtTokenUtil;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil tokenUtil;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService,@Lazy AuthenticationManager authenticationManager, JwtTokenUtil tokenUtil) {
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
        this.tokenUtil = tokenUtil;
    }

    @PostMapping
    public ResponseEntity<Gradjanin> createNew(@RequestBody Gradjanin gradjanin) throws Exception {
        var savedGradjanin = authenticationService.createNew(gradjanin);
        return new ResponseEntity<Gradjanin>(savedGradjanin, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority({'GRADJANIN'})")
    @GetMapping("/{id}")
    public ResponseEntity<Gradjanin> getById(@PathVariable UUID id) throws Exception {
        var savedGradjanin = authenticationService.read(id);
        return new ResponseEntity<Gradjanin>(savedGradjanin, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse authenticate(@RequestBody @Valid AuthRequest request) throws Exception {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenUtil.generateToken(authentication);
        return new AuthResponse(token);
    }
}
