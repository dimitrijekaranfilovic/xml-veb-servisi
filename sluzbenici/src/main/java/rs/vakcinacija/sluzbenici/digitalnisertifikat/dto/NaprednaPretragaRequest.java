package rs.vakcinacija.sluzbenici.digitalnisertifikat.dto;

import lombok.Data;
import rs.vakcinacija.zajednicko.metadata.query.SparqlParameter;

@Data
public class NaprednaPretragaRequest {
    @SparqlParameter(name = "doza_1_tip", type = "string")
    private String doseOneType;
    @SparqlParameter(name = "doza_2_tip", type = "string")
    private String doseTwoType;

    @SparqlParameter(name = "doza_1_datum", type = "date")
    private String doseOneDate;
    @SparqlParameter(name = "doza_1_datum", type = "date")
    private String doseTwoDate;

    @SparqlParameter(name = "doza_1_ustanova", type = "string")
    private String doseOnePlace;
    @SparqlParameter(name = "doza_2_ustanova", type = "string")
    private String doseTwoPlace;

    @SparqlParameter(name = "doza_1_broj_serije", type = "string")
    private String doseOneSeriesNumber;
    @SparqlParameter(name = "doza_2_broj_serije", type = "string")
    private String doseTwoSeriesNumber;

    @SparqlParameter(name = "datum_izdavanja", type = "date")
    private String issueDate;
    @SparqlParameter(name = "email", type = "string")
    private String email;

    private String query = "";
}
