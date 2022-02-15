package rs.vakcinacija.sluzbenici.digitalnisertifikat.dto;

import lombok.Data;
import rs.vakcinacija.zajednicko.metadata.query.SparqlParameter;

@Data
public class NaprednaPretragaRequest {
    @SparqlParameter(name = "doza_1_tip", type = "string")
    private String doseOneType = null;
    @SparqlParameter(name = "doza_2_tip", type = "string")
    private String doseTwoType = null;

    @SparqlParameter(name = "doza_1_datum", type = "date")
    private String doseOneDate = null;
    @SparqlParameter(name = "doza_1_datum", type = "date")
    private String doseTwoDate = null;

    @SparqlParameter(name = "doza_1_ustanova", type = "string")
    private String doseOnePlace = null;
    @SparqlParameter(name = "doza_2_ustanova", type = "string")
    private String doseTwoPlace = null;

    @SparqlParameter(name = "doza_1_broj_serije", type = "string")
    private String doseOneSeriesNumber = null;
    @SparqlParameter(name = "doza_2_broj_serije", type = "string")
    private String doseTwoSeriesNumber = null;

    @SparqlParameter(name = "datum_izdavanja", type = "date")
    private String issueDate = null;
}
