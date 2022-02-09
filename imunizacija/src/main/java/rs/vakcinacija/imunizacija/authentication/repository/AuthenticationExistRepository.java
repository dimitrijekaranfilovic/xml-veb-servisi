package rs.vakcinacija.imunizacija.authentication.repository;

import org.exist.xmldb.EXistResource;
import org.springframework.stereotype.Component;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import rs.vakcinacija.imunizacija.authentication.exception.UserAlreadyExistsException;
import rs.vakcinacija.imunizacija.authentication.model.Gradjanin;
import rs.vakcinacija.zajednicko.data.connection.ExistConnectionProvider;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;

import java.io.ByteArrayInputStream;
import java.util.UUID;

@Component
public class AuthenticationExistRepository extends ExistRepository<Gradjanin> {

    protected AuthenticationExistRepository(ExistConnectionProvider connectionProvider) {
        super("korisnici", Gradjanin.class, connectionProvider);
    }

    @Override
    public UUID save(Gradjanin gradjanin) throws Exception, UserAlreadyExistsException {
        ResourceSet results = runXPathQuery("//email[text()='" + gradjanin.getEmail().getValue() + "']");
        ResourceIterator i = results.getIterator();
        if (i.hasMoreResources()) {
            throw new UserAlreadyExistsException("Gradjanin sa datim email-om vec postoji.");
        }
        return super.save(gradjanin);
    }

    public Gradjanin getByEmail(String email) throws Exception {
        ResourceSet results = runXPathQuery("//gradjanin[email[text()='" + email + "']]");
        ResourceIterator i = results.getIterator();
        Resource res = null;

        while (i.hasMoreResources()) {
            try {
                res = i.nextResource();
                return entityManager.unmarshall(new ByteArrayInputStream(res.getContent().toString().getBytes()));

            } finally {
                try {
                    ((EXistResource) res).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
        return null;
    }

    public boolean checkEmailPassword(String email, String password) throws Exception {
        ResourceSet results = runXPathQuery("//email[text()='" + email + "'] and //password[text()='" + password + "']");

        ResourceIterator i = results.getIterator();
        Resource res = null;

        while (i.hasMoreResources()) {
            try {
                res = i.nextResource();
                return Boolean.parseBoolean((String) res.getContent());

            } finally {
                try {
                    ((EXistResource) res).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }

        return false;
    }
}
