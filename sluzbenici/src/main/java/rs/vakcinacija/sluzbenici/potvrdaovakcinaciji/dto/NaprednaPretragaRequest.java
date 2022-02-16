package rs.vakcinacija.sluzbenici.potvrdaovakcinaciji.dto;

import lombok.Data;
import rs.vakcinacija.zajednicko.metadata.query.SparqlParameter;

@Data
public class NaprednaPretragaRequest {
    @SparqlParameter(name = "email", type = "string")
    private String email;

    @SparqlParameter(name = "ustanova", type = "string")
    private String place;

    @SparqlParameter(name = "datum_izdavanja", type = "date")
    private String issueDate;

    @SparqlParameter(name = "sifra_dokumenta", type = "string")
    private String documentId;

    private String query = "";
}
