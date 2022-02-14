package rs.vakcinacija.imunizacija.saglasnost.repository;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import rs.vakcinacija.imunizacija.saglasnost.model.SaglasnostZaSprovodjenjeImunizacije;
import rs.vakcinacija.zajednicko.data.connection.ExistConnectionProvider;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class SaglasnostExistRepository extends ExistRepository<SaglasnostZaSprovodjenjeImunizacije> {

    @Autowired
    public SaglasnostExistRepository(ExistConnectionProvider connectionProvider) {
        super("saglasnost", SaglasnostZaSprovodjenjeImunizacije.class, connectionProvider);
    }
}
