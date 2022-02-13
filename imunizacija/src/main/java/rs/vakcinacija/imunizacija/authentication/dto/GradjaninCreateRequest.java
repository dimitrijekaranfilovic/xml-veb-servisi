package rs.vakcinacija.imunizacija.authentication.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlElement;

@Data
public class GradjaninCreateRequest {

    @NotBlank
    @XmlElement(name = "name", required = true)
    private String name;

    @NotBlank
    @XmlElement(name = "surname", required = true)
    private String surname;

    @NotBlank
    @XmlElement(name = "email", required = true)
    private String email;

    @NotBlank
    @XmlElement(name = "password", required = true)
    private String rdfpassword;
}
