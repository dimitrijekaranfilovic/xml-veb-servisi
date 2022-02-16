package rs.vakcinacija.sluzbenici.saglasnost.dto;

import lombok.Data;
import rs.vakcinacija.zajednicko.metadata.query.SparqlParameter;

@Data
public class NaprednaPretragaRequest {
    @SparqlParameter(name = "email", type = "string")
    private String email;

    private String query = "";
}
