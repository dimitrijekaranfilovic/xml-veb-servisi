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
import rs.vakcinacija.imunizacija.authentication.dto.GradjaninCreateRequest;
import rs.vakcinacija.imunizacija.authentication.model.Gradjanin;
import rs.vakcinacija.imunizacija.authentication.service.AuthenticationService;
import rs.vakcinacija.imunizacija.authentication.support.GradjaninCreateRequestToGradjanin;
import rs.vakcinacija.imunizacija.authentication.support.GradjaninToGradjaninCreateRequest;
import rs.vakcinacija.imunizacija.config.jwt.JwtTokenUtil;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil tokenUtil;
    private final GradjaninToGradjaninCreateRequest gradjaninToGradjaninCreateRequest;
    private final GradjaninCreateRequestToGradjanin gradjaninCreateRequestToGradjanin;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService,
                                    @Lazy AuthenticationManager authenticationManager,
                                    JwtTokenUtil tokenUtil,
                                    GradjaninToGradjaninCreateRequest gradjaninToGradjaninCreateRequest,
                                    GradjaninCreateRequestToGradjanin gradjaninCreateRequestToGradjanin) {
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
        this.tokenUtil = tokenUtil;
        this.gradjaninToGradjaninCreateRequest = gradjaninToGradjaninCreateRequest;
        this.gradjaninCreateRequestToGradjanin = gradjaninCreateRequestToGradjanin;
    }

    @PostMapping
    public ResponseEntity<GradjaninCreateRequest> createNew(@RequestBody @Valid GradjaninCreateRequest gradjanin) throws Exception {
        if(gradjanin.getName().trim().equals("") || gradjanin.getSurname().trim().equals("") ||
                gradjanin.getEmail().trim().equals("") || gradjanin.getRdfpassword().trim().equals("")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST); // javax validation ne radi :(
        }
        var savedGradjanin = authenticationService.createNew(gradjaninCreateRequestToGradjanin.convert(gradjanin));
        return new ResponseEntity<GradjaninCreateRequest>(gradjaninToGradjaninCreateRequest.convert(savedGradjanin), HttpStatus.CREATED);
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
