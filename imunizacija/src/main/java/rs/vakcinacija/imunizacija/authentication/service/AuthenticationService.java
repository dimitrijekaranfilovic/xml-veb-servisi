package rs.vakcinacija.imunizacija.authentication.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.vakcinacija.imunizacija.authentication.model.Gradjanin;
import rs.vakcinacija.imunizacija.authentication.repository.AuthenticationExistRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AuthenticationService implements UserDetailsService {

    private final AuthenticationExistRepository authenticationRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(AuthenticationExistRepository authenticationRepository, PasswordEncoder passwordEncoder) {
        this.authenticationRepository = authenticationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Gradjanin createNew(Gradjanin gradjanin) throws Exception {
        gradjanin.getRdfpassword().setValue(passwordEncoder.encode(gradjanin.getRdfpassword().getValue()));
        authenticationRepository.save(gradjanin);
        return gradjanin;
    }

    public boolean checkAuthData(String email, String password) throws Exception {
        return authenticationRepository.checkEmailPassword(email, password);
    }

    public Gradjanin read(UUID id) throws Exception {
        return authenticationRepository.read(id).orElseThrow(() -> new NoSuchElementException("User with id: " + id + " is not present."));
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authenticationRepository.getByEmail(username);
    }

    public Gradjanin getByEmail(String email) throws Exception {
        return authenticationRepository.getByEmail(email);
    }
}
