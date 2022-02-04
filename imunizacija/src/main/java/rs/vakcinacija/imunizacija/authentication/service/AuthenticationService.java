package rs.vakcinacija.imunizacija.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.vakcinacija.imunizacija.authentication.model.Gradjanin;
import rs.vakcinacija.imunizacija.authentication.repository.AuthenticationExistRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AuthenticationService {

    private final AuthenticationExistRepository authenticationRepository;

    @Autowired
    public AuthenticationService(AuthenticationExistRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    public Gradjanin createNew(Gradjanin gradjanin) throws Exception {
        authenticationRepository.save(gradjanin);
        return gradjanin;
    }

    public boolean checkAuthData(String email, String password) throws Exception {
        return authenticationRepository.checkEmailPassword(email, password);
    }

    public Gradjanin read(UUID id) throws Exception {
        return authenticationRepository.read(id).orElseThrow(() -> new NoSuchElementException("User with id: " + id + " is not present."));
    }
}
