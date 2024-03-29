package rs.vakcinacija.imunizacija.authentication.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import rs.vakcinacija.zajednicko.model.BaseDocument;
import rs.vakcinacija.zajednicko.model.RDFBoolean;
import rs.vakcinacija.zajednicko.model.RDFString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "gradjanin")
@Getter
@Setter
public class Gradjanin extends BaseDocument implements UserDetails {

    @XmlElement(name = "ime", required = true)
    private RDFString name;

    @XmlElement(name = "prezime", required = true)
    private RDFString surname;

    @XmlElement(name = "email", required = true)
    private RDFString email;

    @XmlElement(name = "password", required = true)
    private RDFString rdfpassword;

    @XmlElement(name = "dao_saglasnost", required = true)
    private RDFBoolean agreedOnVaccination;


    public Gradjanin() {}

    public Gradjanin(RDFString email, RDFString password, RDFString name, RDFString surname) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.rdfpassword = password;
        this.agreedOnVaccination.setValue(false);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of((GrantedAuthority) () -> "GRADJANIN");
    }

    @Override
    public String getPassword() {
        return this.rdfpassword.getValue();
    }

    @Override
    public String getUsername() {
        return this.email.getValue();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
