package rs.vakcinacija.zajednicko.metadata.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SparqlQueryResult<T> {
    private String name;
    private T value;
}
