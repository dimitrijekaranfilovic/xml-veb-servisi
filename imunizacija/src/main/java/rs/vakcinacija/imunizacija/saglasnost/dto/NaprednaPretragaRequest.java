package rs.vakcinacija.imunizacija.saglasnost.dto;

import lombok.Data;
import rs.vakcinacija.zajednicko.metadata.query.SparqlParameter;

@Data
public class NaprednaPretragaRequest {
    @SparqlParameter(name = "email", type = "string")
    private String email;

    @SparqlParameter(name = "cip", type = "string")
    private String vaccine;

    @SparqlParameter(name = "ustanova", type = "string")
    private String place;

    @SparqlParameter(name = "punkt", type = "string")
    private String vaccinationPlace;

    @SparqlParameter(name = "datum_izdavanja", type = "date")
    private String issueDate;

    private String query = "";
}
