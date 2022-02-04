package rs.vakcinacija.imunizacija.authentication.repository;

import org.exist.xmldb.EXistResource;
import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import rs.vakcinacija.imunizacija.authentication.exception.UserAllreadyExistsException;
import rs.vakcinacija.imunizacija.authentication.model.Gradjanin;
import rs.vakcinacija.zajednicko.data.connection.ExistConnectionProvider;
import rs.vakcinacija.zajednicko.data.context.ManagedCollectionAdapter;
import rs.vakcinacija.zajednicko.data.context.ManagedXMLResourceAdapter;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;

import javax.xml.transform.OutputKeys;
import java.util.Optional;
import java.util.UUID;

@Component
public class AuthenticationExistRepository extends ExistRepository<Gradjanin> {

    protected AuthenticationExistRepository(ExistConnectionProvider connectionProvider) {
        super("korisnici", Gradjanin.class, connectionProvider);
    }

    public UUID create(Gradjanin gradjanin) throws Exception {
        ResourceSet results = runXPathQuery("//email[text()='" + gradjanin.getEmail().getValue() + "']");
        ResourceIterator i = results.getIterator();
        if (!i.hasMoreResources()) {
            return save(gradjanin);
        }
        throw new UserAllreadyExistsException("Citizen with that email allready exists.");
    }

    public boolean checkEmailPassword(String email, String password) throws Exception {
        ResourceSet results = runXPathQuery("//email[text()='"+ email +"'] and //password[text()='" + password + "']");

        ResourceIterator i = results.getIterator();
        Resource res = null;

        while(i.hasMoreResources()) {
            try {
                res = i.nextResource();
                return Boolean.parseBoolean((String) res.getContent());

            } finally {
                try {
                    ((EXistResource)res).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }

        return false;
    }
}
